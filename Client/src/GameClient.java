import java.io.*;
import java.net.Socket;
import java.util.Scanner;
public class GameClient {
    /*
    TO DO.
    Class that encapsulates the game's logic. Sequence of states following the established protocol .
     */

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    public GameClient(String host, int port,Socket sckt){
        this.socket = sckt;
        try{
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }catch(IOException e){
            throw new RuntimeException("Error connecting to server: "+e.getMessage());

        }
    }

    public void send(String message){
        out.println(message);
    }
    public String recieve(){
        try{
            return in.readLine();

        }catch(IOException e){

            throw new RuntimeException("Error recieving message from server: "+e.getMessage());
        }
    }
    public void disconnect(){
        try{
            this.socket.close();
        }catch(IOException e){
            throw new RuntimeException("Error closing connection: "+ e.getMessage());
        }
    }

    public void playGame(){
        //Aquí jugaremos al juego

    }

}
