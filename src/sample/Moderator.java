package sample;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import javax.swing.*;

import static sample.DBmanager.*;

public class Moderator {

    @FXML
    private ListView<String> listviewww;

    @FXML
    private Button buttonToLogOut;

    @FXML
    private Button buttonToMes;


    @FXML
    void initialize() {

        buttonToLogOut.setOnAction(event -> {
            change(buttonToLogOut, "sample");
        });
        buttonToMes.setOnAction(event -> {
            //  change(buttonToMes, "mo");
            try {
                ServerSocket serverSocket = new ServerSocket(1500);

                System.out.println("Moderator online");
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                Scanner in = new Scanner(System.in);

                while (true) {
                    String message = (String) input.readObject();
                    System.out.println(message);
                    // JOptionPane.showMessageDialog(null, message, "Everything went well", JOptionPane.PLAIN_MESSAGE);


                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        });
       Th t = new Th();
       t.start();
    }

    public void change(Button button, String url) {
        button.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/" + url + ".fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    class Th extends Thread{
        public void run(){
            try{
                Connection dbConnection;
                Class.forName("com.mysql.jdbc.Driver");
                String connectionString = "jdbc:mysql://" + dbhost + ":"
                        + dbport + "/" + dbname;
                Class.forName("com.mysql.jdbc.Driver");
                dbConnection = DriverManager.getConnection(connectionString
                        , dbuser, dbpass);
                Statement statement = dbConnection.createStatement();
                int u = 0;
                ResultSet resultSet = statement.executeQuery("SELECT *from doc");

                while (resultSet.next()) {
                    String name = resultSet.getString(1);
                    int price = resultSet.getInt(2);
                    String time = resultSet.getString(3);
                    String tot = "Service: " + name + ", price: " + price + ", time: " + time;
                    TimeUnit.SECONDS.sleep(2);
                    listviewww.getItems().add(tot);
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

