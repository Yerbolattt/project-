package sample;

import java.io.IOException;
import java.io.ObjectInputStream;
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

public class mod {

    @FXML
    private Button buttonToLogoutInMo;

    @FXML
    private ListView<String> listMe;

    @FXML
    private TextField textMeInMo;

    @FXML
    private Button buttonToSendino;

    @FXML
    void initialize() throws IOException {


        buttonToLogoutInMo.setOnAction(event -> {
            change(buttonToLogoutInMo, "Mod");
        });
buttonToSendino.setOnAction(event -> {
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


}
    } catch (IOException | ClassNotFoundException e) {
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
