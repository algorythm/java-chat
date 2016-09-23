/**
 * Created by awo on 23/09/16.
 */
import java.net.*;
import java.io.*;

public class Server implements Runnable {
    private ServerSocket    server   = null;
    private Thread          thread   = null; // Update 1
    private ServerThread    client   = null; // Update 2

    public Server (int port) {
        try {
            System.out.println("Binding to port " + port + ", please wait...");

            // Binding server to a new port on the network.
            server = new ServerSocket(port);

            System.out.println("Server started: " + server);

            start();

        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public void run() {
        while (thread != null) {
            try {
                System.out.println("Waiting for a client ...");
                addThread(server.accept());
            } catch (IOException ie) {
                System.out.println("Acceptance Error: " + ie);
            }
        }
    }

    public void addThread (Socket socket) {
        System.out.println("Client accepted: " + socket);
        client = new ServerThread(this, socket);

        try {
            client.open();
            client.start();
        } catch (IOException ioe) {
            System.out.println("Error opening thread: " + ioe);
        }
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    public void stop() {
        if (thread != null) {
            thread.stop();
            thread = null;
        }
    }
}
