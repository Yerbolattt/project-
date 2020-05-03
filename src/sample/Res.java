package sample;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Res {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonToLogoutInRe;

    @FXML
    private ListView<String> listMe;

    @FXML
    private TextField textMe;

    @FXML
    private Button buttonToSend;

    @FXML
    void initialize() throws IOException {


        buttonToLogoutInRe.setOnAction(event -> {
         change(buttonToLogoutInRe, "App");
     });
        buttonToSend.setOnAction(event -> {
            Socket socket = null;
            try {
                socket = new Socket("127.0.0.1",1500);

            System.out.println("Client online");
            Scanner in = new Scanner(System.in);
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            try {
while(true){

                        String text = textMe.getText();
                        output.writeObject(text);

                }

            } catch (IOException e) {
                e.printStackTrace();
            } } catch (IOException e) {
            e.printStackTrace();
        }
        });

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
}
