import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        int port = 8887;
        try {
            port = Integer.parseInt(args[0]);
        } catch (Exception ex) {
            // Empty catch, bind to default if none provided
        }
        ChatServer s = new ChatServer(port);
        s.start();
        System.out.println("Server started on port: " + s.getPort());

        BufferedReader message = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String in = message.readLine();
            s.broadcast(in);
        }
    }
}
