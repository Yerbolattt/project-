package sample;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        try {
            Socket socket = new Socket("127.0.0.1",1500);
            System.out.println("Client online");
            Scanner in = new Scanner(System.in);
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            while(true){
                String text= in.nextLine();
                output.writeObject(text);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
