package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import animation.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.Stage;

import javax.swing.*;

public class ButRegCla {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView im;

    @FXML
    private TextField textToLoginInRegistr;

    @FXML
    private TextField textName;

    @FXML
    private TextField textSurname;

    @FXML
    private PasswordField textToPassInRegistr;

    @FXML
    private PasswordField textReenterPass;

    @FXML
    private RadioButton radioMale;

    @FXML
    private RadioButton radioFemale;

    @FXML
    private Button buttonToRegistrationInRegistr;

    @FXML
    private Button buttonToBack;

    @FXML
    private TextField textNumber;

    @FXML
    private TextField textQuestion;

    @FXML
    private TextField textCity;

    @FXML
    private TextField textAnswer;


    @FXML
    void initialize() {
        DBHandl dbHandl = new DBHandl();
        buttonToRegistrationInRegistr.setOnAction(event -> {
            if (textToPassInRegistr.getText().equals(textReenterPass.getText()) == true && textReenterPass.getText().equals("")==false
                    && textName.getText().equals("")==false && textSurname.getText().equals("")==false && textToLoginInRegistr.getText().equals("")==false &&
                    textNumber.getText().equals("")==false && textQuestion.getText().equals("")==false
                    && textAnswer.getText().equals("")==false && textCity.getText().equals("")==false) {
                String gender;
                if (radioFemale.isSelected()) {
                    gender = "Female";
                } else {
                    gender = "Male";
                }

                String role = "User";
                Users users = new Users(textName.getText(), textSurname.getText(), textToLoginInRegistr.getText(),
                        textToPassInRegistr.getText(), textAnswer.getText(), textQuestion.getText(),
                        Integer.parseInt(textNumber.getText()), textCity.getText(),gender, role);
                dbHandl.signUpUser(users);
                textToPassInRegistr.setText("");
                textToLoginInRegistr.setText("");
                textSurname.setText("");
                textName.setText("");
                textReenterPass.setText("");
                textCity.setText("");
                textAnswer.setText("");
                textQuestion.setText("");
                textNumber.setText("");
                String d="You have successfully registered\n" + "Welcome!!";
                JOptionPane.showMessageDialog(null, d, "Congratulations", JOptionPane.PLAIN_MESSAGE);
                buttonToRegistrationInRegistr.getScene().getWindow().hide();
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

            } else if (textToPassInRegistr.getText().equals(textReenterPass.getText()) == false) {
                Shake userReenterAnim = new Shake(textReenterPass);
                Shake userPassAnim = new Shake(textToPassInRegistr);
                userReenterAnim.playAnim();
                userPassAnim.playAnim();
            }
            else {
                String d = "Error\n" + "Try again";
                JOptionPane.showMessageDialog(null, d, "Welcome", JOptionPane.PLAIN_MESSAGE);
            }

        });
        buttonToBack.setOnAction(event -> {
            change(buttonToBack, "sample");
        });

    }

    private void SignUpUser() {
        DBHandl dbHandl = new DBHandl();
        String name = textName.getText();
        String surname = textSurname.getText();
        String login = textToLoginInRegistr.getText();
        String password = textToPassInRegistr.getText();
        String question = textQuestion.getText();
        String answer = textAnswer.getText();
        int num = Integer.parseInt(textNumber.getText());
        String city = textCity.getText();
        String gender = "";
        if (radioFemale.isSelected()) {
            gender = "Female";
        } else
            gender = "Male";

        String role = "User";
        Users user = new Users(name, surname, login, password,answer, question, num, city,gender ,role);

        dbHandl.signUpUser(user);
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