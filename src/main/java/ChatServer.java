package main.java;

import main.java.blackjack.BlackjackGame;
import main.java.blackjack.actions.Hit;
import main.java.blackjack.actions.Stay;
import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;


public class ChatServer extends WebSocketServer {

    private final HashMap<WebSocket, Game> connections = new HashMap<WebSocket, Game>();
    private final ActionHandler actionHandler = new ActionHandler();

    public ChatServer(int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
        this.SubscribeActions();
    }

    public ChatServer(InetSocketAddress address) {
        super(address);
        this.SubscribeActions();
    }

    public ChatServer(int port, Draft_6455 draft) {
        super(new InetSocketAddress(port), Collections.<Draft>singletonList(draft));
        this.SubscribeActions();
    }

    private void SubscribeActions() {
        try {
            this.actionHandler.subscribe(Hit.class);
            this.actionHandler.subscribe(Stay.class);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        conn.send("Welcome to the server!"); //This method sends a message to the new client
        System.out.println(conn.getRemoteSocketAddress().getAddress().getHostAddress() + " has created a new game!");
        connections.put(conn, new BlackjackGame(416));
        conn.send("New Game!");
        connections.get(conn).ShowPlayerHand();
        connections.get(conn).ShowDealerHand();
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println(conn + " has left the game!");
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        broadcast(message);
        System.out.println(conn + ": " + message);
        try {
            this.actionHandler.dispatch(new ActionContext(connections.get(conn)), message);
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            conn.send("Invalid command");
        }
    }

    @Override
    public void onMessage(WebSocket conn, ByteBuffer message) {
        broadcast(message.array());
        System.out.println(conn + ": " + message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
    }

    @Override
    public void onStart() {
        System.out.println("Server started!");
        setConnectionLostTimeout(0);
        setConnectionLostTimeout(100);
    }
}
