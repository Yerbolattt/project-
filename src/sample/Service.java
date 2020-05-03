package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import static sample.DBmanager.*;

public class Service {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> listSer;

    @FXML
    private Button buttonToBackInSer;

    @FXML
    private Button buttonToOrderService;

    @FXML
    void initialize() {
       buttonToBackInSer.setOnAction(event -> {
           change(buttonToBackInSer);change("App");
       });
       buttonToOrderService.setOnAction(event -> {
           change(buttonToOrderService);change("ord");
       });
        try {
            Connection dbConnection;
            Class.forName("com.mysql.jdbc.Driver");
            String connectionString = "jdbc:mysql://" + dbhost + ":"
                    + dbport + "/" + dbname;
            Class.forName("com.mysql.jdbc.Driver");
            dbConnection = DriverManager.getConnection(connectionString
                    , dbuser, dbpass);
            Statement statement = dbConnection.createStatement();
            int u = 0;
            String d = "";
            String q = null, a = null;
            ResultSet resultSet = statement.executeQuery("SELECT *from service");
            while (resultSet.next()) {
                String res =resultSet.getString(1) +": " + resultSet.getString(2) + ", price: " + resultSet.getString(3);
                listSer.getItems().add(res);
            }
        } catch (Exception e ){
            e.getMessage();
        }
    }
    public void change(Button button) {
        button.getScene().getWindow().hide();
    }
    public void change(String url){
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
