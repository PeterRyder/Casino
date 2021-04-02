package main.java;

import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;


public class ChatServer extends WebSocketServer {

    private final HashMap<WebSocket, Game> connections = new HashMap<WebSocket, Game>();

    public ChatServer(int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
    }

    public ChatServer(InetSocketAddress address) {
        super(address);
    }

    public ChatServer(int port, Draft_6455 draft) {
        super(new InetSocketAddress(port), Collections.<Draft>singletonList(draft));
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        conn.send("Welcome to the server!"); //This method sends a message to the new client
        System.out.println(conn.getRemoteSocketAddress().getAddress().getHostAddress() + " has created a new game!");
        connections.put(conn, new BlackjackGame(408));
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
        if (message.equals("hit")) {
            connections.get(conn).DealToPlayer();
            connections.get(conn).ShowPlayerHand();
            connections.get(conn).ShowDealerHand();
        }
        else if (message.equals("stay")) {
            connections.get(conn).ShowPlayerHand();
            connections.get(conn).ShowDealerHand();
        }
        else if (message.equals("double")) {

        }
        else if (message.equals("split")) {

        }
        else if (message.equals("surrender")) {

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
