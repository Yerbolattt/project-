package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;

import static sample.DBmanager.*;

public class Delete {


    @FXML
    private TextField textToLoginInDel;

    @FXML
    private Button buttonToDeleteInDel;

    @FXML
    private ListView<String> list;

    @FXML
    private Label labelToDelete;

    @FXML
    private Button buttonToExit;

    @FXML
    void initialize() {
        buttonToDeleteInDel.setOnAction(event -> {
            try {
                if(!textToLoginInDel.getText().trim().equals("")) {
                    removeModerator(textToLoginInDel.getText());
                    textToLoginInDel.setText("");
                }
                else{
                    String d = "Enter login";
                    JOptionPane.showMessageDialog(null, d, "Attention", JOptionPane.PLAIN_MESSAGE);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        buttonToExit.setOnAction(event -> {
            change(buttonToExit, "Admi");
        });
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
            ResultSet resultSet = statement.executeQuery("SELECT *from account");
            while (resultSet.next()) {
                String test = resultSet.getString(11);
                if (!test.equals("Admin")){
                    String login = resultSet.getString(4);
                    String rel = resultSet.getString(11);
                    String tot = "Login: " + login + " | Role: " + rel;
                    list.getItems().add(tot);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void removeModerator(String login) throws SQLException, ClassNotFoundException {
        Connection connection;
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://" + dbhost + ":"
                + dbport + "/" + dbname;
        connection = DriverManager.getConnection(url , dbuser, dbpass);
        PreparedStatement pr = connection.prepareStatement("DELETE FROM account WHERE login=?");
        pr.setString(1,login);
        pr.executeUpdate();
        pr.close();
        String d = "You delete " + login;
        JOptionPane.showMessageDialog(null, d, "Everything went well", JOptionPane.PLAIN_MESSAGE);

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

