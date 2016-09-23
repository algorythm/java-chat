/**
 * Created by awo on 23/09/16.
 */

// http://pirate.shu.edu/~wachsmut/Teaching/CSAS2214/Virtual/Lectures/chat-client-server.html

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        if (args.length == 1 || args.length == 2)
        {
            if (args.length == 1) {
                System.out.println("--- Server ---");
                Server server = new Server(Integer.parseInt(args[0]));
            } else if (args.length == 2) {
                System.out.println("--- Client ---");
                Client client = new Client(args[0], Integer.parseInt(args[1]));
            }
        }
        else
        {
            System.out.println("Usage: ");
            System.out.println("   Server: java ChatServer [port] server");
            System.out.println("   Client: java ChatServer [host] [port]");
        }
    }
}
