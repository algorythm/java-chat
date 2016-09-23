/**
 * Created by awo on 23/09/16.
 */
import java.net.*;
import java.io.*;

public class Server implements Runnable {
    private Socket          socket   = null;
    private ServerSocket    server   = null;
    private Thread          thread   = null; // Update 1
    private DataInputStream streamIn = null;

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

    public void run() {
        while (thread != null) {
            try {
                System.out.println("Waiting for a client ...");

                // I don't know what it does.
                socket = server.accept();

                System.out.println("Client accepted: " + socket);
                open();

                boolean done = false;
                while(!done) {
                    try {
                        String line = streamIn.readUTF();
                        System.out.println(line);
                        done = line.equals(".bye");
                    } catch (IOException ioe) {
                        done = true;
                    }
                }

                close();

            } catch (IOException ie) {
                System.out.println("Acceptance Error: " + ie);
            }
        }
    }

    public void open() throws IOException {
        streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
    }

    public void close() throws IOException {
        if (socket   != null) socket.close();
        if (streamIn != null) streamIn.close();
    }
}
