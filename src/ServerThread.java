/**
 * Created by awo on 23/09/16.
 */
import java.net.*;
import java.io.*;

public class ServerThread extends Thread {
    private Socket          socket   = null;
    private Server          server   = null;
    private int             ID       = -1;
    private DataInputStream streamIn = null;

    public ServerThread(Server _server, Socket _socket) {
        this.server = _server;
        this.socket = _socket;
        this.ID     = this.socket.getPort();
    }

    public void run() {
        System.out.println("Server Thread " + ID + " running.");
        while (true) {
            try {
                System.out.println(streamIn.readUTF());
            } catch (IOException ioe) {
                // Nothing???
            }
        }
    }

    public void open() throws IOException {
        streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
    }

    public void close() throws IOException {
        if (socket != null)   socket.close();
        if (streamIn != null) streamIn.close();
    }
}
