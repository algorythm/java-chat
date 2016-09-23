/**
 * Created by awo on 23/09/16.
 */
import java.net.*;
import java.io.*;

public class Server {
    private Socket          socket   = null;
    private ServerSocket    server   = null;
    private DataInputStream streamIn = null;

    public Server (int port) {
        try {
            System.out.println("Binding to port " + port + ", please wait...");

            // Binding server to a new port on the network.
            server = new ServerSocket(port);

            System.out.println("Server started: " + server);
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
                } catch (Exception ex) {
                    done = true;
                }
            }
            close();

        } catch (IOException ioe) {
            System.out.println(ioe);
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
