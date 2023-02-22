import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.IllegalBlockingModeException;

public class Server {
    public static final String INIT_ERROR = "Server should be initialized with -p <port>";

    public static void main(String[] args) throws IOException {

        if (args.length != 2) {
            throw new IllegalArgumentException("Wrong amount of arguments.\n" + INIT_ERROR);
        }

        if (!args[0].equals("-p")) {
            throw new IllegalArgumentException("Wrong argument keyword.\n"+INIT_ERROR);
        }

        int port;


        try {
            port = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("<port> should be an Integer. Use 0 for automatic allocation.");
        }

        GameHandler gh = new GameHandler();

        gh.init(port);

        /*
        TO DO:
        Create a new GameHandler for every client.
        */

        gh.play();




    }
}