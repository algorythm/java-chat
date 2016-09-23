/**
 * Created by awo on 23/09/16.
 */
public class App {
    public static void main(String[] args) {
        if (args.length != 1 || args.length != 2)
        {
            System.out.println("Usage: ");
            System.out.println("   Server: $ java -jar chat.jar <port>");
            System.out.println("   Client: $ java -jar chat.jar <ip-address|hostname> <port>");
        }
        if (args.length == 1)
        {
            Server server = null;

            System.out.println("==== SERVER ====");
            System.out.println();

            server = new Server(Integer.parseInt(args[0]));
        }
        if (args.length == 2)
        {
            Client client = null;

            System.out.println("==== CLIENT ====");
            System.out.println();

            client = new Client(args[0], Integer.parseInt(args[1]));
        }
    }
}
