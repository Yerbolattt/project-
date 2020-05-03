package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

import animation.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.swing.*;

import static sample.DBmanager.*;

public class Admin{

    @FXML
    private ImageView im;

    @FXML
    private TextField textToLoginInAdmi;

    @FXML
    private TextField textNameInAdmi;

    @FXML
    private TextField textSurnameInAdmi;

    @FXML
    private PasswordField textToPassInAdmi;

    @FXML
    private PasswordField textReenterPassInAdmi;

    @FXML
    private RadioButton radioMaleInAdmi;

    @FXML
    private RadioButton radioFemaleInAdmi;

    @FXML
    private Button buttonToAddModeratorInAdmi;

    @FXML
    private Button buttonToBackInAdmi;

    @FXML
    private TextField textNumberInAdmi;

    @FXML
    private TextField textQuestionInAdmi;

    @FXML
    private TextField textCityInAdmi;

    @FXML
    private TextField textAnswerInAdmi;

    @FXML
    private Button buttonToRemoveModInAdmi;

    @FXML
    private ListView<String> src;


    @FXML
    void initialize() {
        buttonToBackInAdmi.setOnAction(event -> {
            change(buttonToBackInAdmi, "sample");
        });
        DBHandl dbHandl = new DBHandl();
        buttonToAddModeratorInAdmi.setOnAction(event -> {
            if (textToPassInAdmi.getText().equals(textReenterPassInAdmi.getText()) == true && textReenterPassInAdmi.getText().equals("")==false
                    && textNameInAdmi.getText().equals("")==false && textSurnameInAdmi.getText().equals("")==false && textToLoginInAdmi.getText().equals("")==false &&
                    textNumberInAdmi.getText().equals("")==false && textQuestionInAdmi.getText().equals("")==false
                    && textAnswerInAdmi.getText().equals("")==false && textCityInAdmi.getText().equals("")==false) {
                String gender;
                if (radioFemaleInAdmi.isSelected()) {
                    gender = "Female";
                } else {
                    gender = "Male";
                }

                String role = "Moderator";
                Users user = new Users(textNameInAdmi.getText(), textSurnameInAdmi.getText(), textToLoginInAdmi.getText(),
                        textToPassInAdmi.getText(), textAnswerInAdmi.getText(), textQuestionInAdmi.getText(),
                        Integer.parseInt(textNumberInAdmi.getText()), textCityInAdmi.getText(),gender, role);
                dbHandl.signUpUser(user);
                String tot = "Login: " + textToLoginInAdmi.getText() + " | Password: " + textToPassInAdmi.getText() +  " | Role: " + role;
                src.getItems().add(tot);
                textToPassInAdmi.setText("");
                textToLoginInAdmi.setText("");
                textSurnameInAdmi.setText("");
                textNameInAdmi.setText("");
                textReenterPassInAdmi.setText("");
                textCityInAdmi.setText("");
                textAnswerInAdmi.setText("");
                textQuestionInAdmi.setText("");
                textNumberInAdmi.setText("");
                String d="You have successfully registered new Moderator";
                JOptionPane.showMessageDialog(null, d, "Congratulations", JOptionPane.PLAIN_MESSAGE);

            } else if (textToPassInAdmi.getText().equals(textReenterPassInAdmi.getText()) == false) {
                Shake userReenterAnim = new Shake(textReenterPassInAdmi);
                Shake userPassAnim = new Shake(textToPassInAdmi);
                userReenterAnim.playAnim();
                userPassAnim.playAnim();
            }
            else {
                String d = "Error\n" + "Try again";
                JOptionPane.showMessageDialog(null, d, "Welcome", JOptionPane.PLAIN_MESSAGE);
            }

        });
        buttonToRemoveModInAdmi.setOnAction(event -> {
            change(buttonToRemoveModInAdmi, "del");
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
            ResultSet resultSet = statement.executeQuery("SELECT *from account");

            while (resultSet.next()) {
                String test = resultSet.getString(11);
                if (!test.equals("Admin")) {
                    String login = resultSet.getString(4);
                    String pas = resultSet.getString(5);
                    String rol = resultSet.getString(11);
                    String tot = "Login: " + login + " | Password: " + pas + " | Role: " + rol;
                    src.getItems().add(tot);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

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
