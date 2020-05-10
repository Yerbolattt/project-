package sample;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import animation.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;

import static sample.DBmanager.*;


public class Order implements Inter{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> listm;

    @FXML
    private TextField textToTime;

    @FXML
    private TextField textToService;

    @FXML
    private Button buttonToCancelINOrdering;

    @FXML
    private Button buttonToOrderInOrder;

    @FXML
    void initialize() {

        buttonToCancelINOrdering.setOnAction(event -> {
            change(buttonToCancelINOrdering,"ser");
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
                listm.getItems().add(res);
            }
        } catch (Exception e ){
            e.getMessage();
    }
        buttonToOrderInOrder.setOnAction(event -> {
            String id = textToService.getText();
            String time = textToTime.getText();
            String n = "";
            int price = 0;
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
                    int idd = resultSet.getInt(1);
                    if (idd == Integer.parseInt(id) && textToService.getText().equals("") == false) {
                        n = resultSet.getString(2);
                        price = resultSet.getInt(3);
                        String ds = "You signed up successfully";
                        JOptionPane.showMessageDialog(null, ds, "Congratulate!", JOptionPane.PLAIN_MESSAGE);
                        change(buttonToOrderInOrder, "ser");
                        String insert = "INSERT INTO " + "doc" + " (" +
                                "name" + "," + "price" + "," + "time" + ")" +
                                "VALUES(?,?,?)";
                        PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
                        try {
                            preparedStatement.setString(1, n);
                            preparedStatement.setString(2, String.valueOf(price));
                            preparedStatement.setString(3, textToTime.getText());
                            preparedStatement.executeUpdate();

                        } catch (Exception e) {
                            e.getMessage();
                        }
                    } else if (idd != Integer.parseInt(id)) {
                        Shake an = new Shake(textToService);
                        an.playAnim();
                    }
                }

            /*
                String insert = "INSERT INTO " + "doc" + " (" +
                        "name" + "," + "price" + "," + "time" + ")"  +
                        "VALUES(?,?,?)";
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
try{
    preparedStatement.setString(1,n);
    preparedStatement.setString(2, String.valueOf(price));
    preparedStatement.setString(3,textToTime.getText());
    preparedStatement.executeUpdate();

}catch (Exception e){
    e.getMessage();
}
                } catch (Exception e){
                e.getMessage();
            }

             */
                //String d = "You signed up successfully";
                //JOptionPane.showMessageDialog(null, d, "Congratulate!", JOptionPane.PLAIN_MESSAGE);
                //change(buttonToOrderInOrder,"ser");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
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

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://"+ dbhost + ":"
                + dbport + "/" + dbname;
        Class.forName("com.mysql.jdbc.Driver");
        Connection dbConnection = DriverManager.getConnection(connectionString
                , dbuser, dbpass);
        return dbConnection;
    }
   }


