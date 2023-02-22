import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.IllegalBlockingModeException;

public class GameHandler implements Runnable{

    /*
    TO DO
    Protocol dynamics from Server.
    Methods: run(), init(), play().
     */
    private ServerSocket ss = null;
    private Socket socket = null;

    @Override
    public void run() {
        System.out.println("The thread is running");
    }

    public void init(int port) {
        try {
            if(ss == null) ss = new ServerSocket(port);
            System.out.println("Server up & listening on port "+port+"...\nPress Cntrl + C to stop.");
        } catch (IOException e) {
            throw new RuntimeException("I/O error when opening the Server Socket:\n" + e.getMessage());
        }

        try {
            if(socket == null) socket = ss.accept();
            System.out.println("Client accepted");
        } catch (IOException e) {
            throw new RuntimeException("I/O error when accepting a client:\n" + e.getMessage());
        } catch (SecurityException e) {
            throw new RuntimeException("Operation not accepted:\n" + e.getMessage());
        } catch (IllegalBlockingModeException e) {
            throw new RuntimeException("There is no connection ready to be accepted:\n" + e.getMessage());
        }
        run();
    }

    public void play() {

        String message = null;

        try {
            DataInputStream data_input = new DataInputStream(socket.getInputStream());
            message = data_input.readUTF();
            System.out.println("The client send the following message:\n" + message);
            ss.close();
            data_input.close();
        } catch (IOException e) {
            throw new RuntimeException("I/O Error when reading the client's message:\n" + e.getMessage());
        }
    }


}