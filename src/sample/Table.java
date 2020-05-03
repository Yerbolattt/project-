package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import static sample.DBmanager.*;

public class Table {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> listw;

    @FXML
    private Button buttonToBackInOerdered;

    @FXML
    void initialize() {
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
            ArrayList<String> s = new ArrayList<>();

          ResultSet resultSet = statement.executeQuery("SELECT *from doc");

            while (resultSet.next()) {
                String name = resultSet.getString(1);
                int price = resultSet.getInt(2);
                String time = resultSet.getString(3);
                String tot = "Service: " + name + ", price: " + price + ", time: " + time ;
                listw.getItems().add(tot);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        buttonToBackInOerdered.setOnAction(event -> {
            change(buttonToBackInOerdered, "App");
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
