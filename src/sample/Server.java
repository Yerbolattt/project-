package sample;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server extends Thread {
    private Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
    }
    public void run(){
        try {

            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            Scanner in = new Scanner(System.in);
            while(true){
                String message = (String)input.readObject();
                System.out.println(message);
                String m = in.nextLine();
                output.writeObject(m);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
