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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.swing.*;

import static sample.DBmanager.*;

public class Controller {

    @FXML
    private ImageView im;

    @FXML
    private TextField textToLogin;

    @FXML
    private PasswordField textToPass;

    @FXML
    private Button buttonToSignIn;

    @FXML
    private Button buttonToRegistration;

    @FXML
    private Button buttonToForgot;

    @FXML
    void initialize() {
        buttonToSignIn.setOnAction(event -> {
            String loginText = textToLogin.getText().trim();
            String passwordText = textToPass.getText().trim();
            if(!loginText.equals("") && !passwordText.equals("")){
                loginUser(loginText, passwordText);
            }

            else {
                String d = "Enter login and password";
                JOptionPane.showMessageDialog(null, d, "Attention", JOptionPane.PLAIN_MESSAGE);
            }
        });
        buttonToRegistration.setOnAction(event -> {
            buttonToRegistration.getScene().getWindow().hide();
            FXMLLoader lod = new FXMLLoader();
            lod.setLocation(getClass().getResource("/sample/ButReg.fxml"));
            try {
                lod.load();
            }
            catch (IOException e){
                e.printStackTrace();
            }
            Parent root = lod.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
        buttonToForgot.setOnAction(event -> {
            buttonToForgot.getScene().getWindow().hide();
            FXMLLoader lod = new FXMLLoader();
            lod.setLocation(getClass().getResource("/sample/ForgotPassword.fxml"));
            try {
                lod.load();
            }
            catch (IOException e){
                e.printStackTrace();
            }
            Parent root = lod.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });


    }

    private void loginUser(String loginText, String passwordText) {
        Connection dbConnection;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String connectionString = "jdbc:mysql://" + dbhost + ":"
                    + dbport + "/" + dbname;
            Class.forName("com.mysql.jdbc.Driver");
            dbConnection = DriverManager.getConnection(connectionString
                    , dbuser, dbpass);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        DBHandl dbHandl = new DBHandl();
        Users user = new Users();
        user.setLogin(loginText);
        user.setPassword(passwordText);
        ResultSet resultSet = dbHandl.getUser(user);
        int count = 0;
        try {
            while (resultSet.next()) {
                String role = resultSet.getString(11);
                String log = resultSet.getString(4);
                String pas = resultSet.getString(5);
                if(log.equals(user.getLogin()) && pas.equals(user.getPassword()) && role.equals("Admin")){
                    count = 1;
                }
                else if(log.equals(user.getLogin()) && pas.equals(user.getPassword()) && role.equals("User")){
                    count = 10;
                }
                else if(log.equals(user.getLogin()) && pas.equals(user.getPassword()) && role.equals("Moderator")){
                    count = 20;
                }
            }
        }
        catch (Exception e){
            try {
                FileWriter writer = new FileWriter("C:\\Users\\ONE\\Desktop\\Java\\ERROR.txt");
                writer.write(e.getMessage());
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        if(count==10){
            buttonToSignIn.getScene().getWindow().hide();
            String insert = "INSERT INTO " + "table_name" + " (" +
                    "name" + ")"  +
                    "VALUES(?)";
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = getDbConnection().prepareStatement(insert);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try{
                preparedStatement.setString(1,loginText);
                preparedStatement.executeUpdate();

            }catch (Exception e){
                e.getMessage();
            }
            FXMLLoader lod = new FXMLLoader();
            lod.setLocation(getClass().getResource("/sample/App.fxml"));
            try {
                lod.load();
            }
            catch (IOException e){
                e.printStackTrace();
            }
            Parent root = lod.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        }
        else if(count==1){
            buttonToSignIn.getScene().getWindow().hide();
            FXMLLoader lod = new FXMLLoader();
            lod.setLocation(getClass().getResource("/sample/Admi.fxml"));
            try {
                lod.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = lod.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
        else if(count == 20){
            buttonToSignIn.getScene().getWindow().hide();
            FXMLLoader lod = new FXMLLoader();
            lod.setLocation(getClass().getResource("/sample/Mod.fxml"));
            try {
                lod.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = lod.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
        else{
            Shake userLogAnim = new Shake(textToLogin);
            Shake userPassAnim = new Shake(textToPass);
            userLogAnim.playAnim();
            userPassAnim.playAnim();
        }
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
