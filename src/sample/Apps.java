package sample;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;


public class Apps  {
    @FXML
    private TextArea textttttt;

    @FXML
    private Button buttonToHome;

    @FXML
    private Button buttonToServices;

    @FXML
    private Button buttonToOpenNewAncho;

    @FXML
    private AnchorPane anchorpaneToNewButtons;

    @FXML
    private Button buttonToAboutClinic;

    @FXML
    private Button buttonToReviews;

    @FXML
    private Button buttonToContacts;

    @FXML
    private Button buttonToLogOut;

    @FXML
    private Button buttonToCloseNewAncho;

    @FXML
    void initialize() {
        textttttt.setText("Dental clinics in Almaty number in the tens. It is difficult to choose truly high-quality services\n among many offers. In order not to experiment with your health and wallet, \nimmediately come to Aibolit.\n" +

                "Almost twenty years of successful work;\n" +
                "Stable position in the market of dental services;\n" +
                "Constant growth and expansion of space, capabilities, functionality, staff;\n" +
                "Own building with a comfortable and thoughtful layout;\n" +
                "Modern and constantly updated equipment;\n" +
                "The staff of narrow specialists, constantly improving their skills;\n" +
                "Affordable dental care that meets international standards.\n" +
                "Dental treatment in Almaty is a complex and multifaceted process. We individually approach each client, \noffer various ways out of the situation and find the optimal solution that satisfies the patient in all respects.\n Being a multidisciplinary dental clinic, we offer a solution to many problems in a short time than\n other dentistry in Almaty, forced to resort to the services of intermediaries.\n This is convenient for our customers and reduces the overall time of assistance.\n");
        buttonToOpenNewAncho.setOnAction(event -> {
            anchorpaneToNewButtons.setVisible(true);
            buttonToOpenNewAncho.setVisible(false);
        });
        buttonToCloseNewAncho.setOnAction(event -> {
            anchorpaneToNewButtons.setVisible(false);
            buttonToOpenNewAncho.setVisible(true);
        });

        buttonToLogOut.setOnAction(event -> {
            change(buttonToLogOut, "sample");
        });
        buttonToReviews.setOnAction(event -> {
            ///change(buttonToReviews, "re");
            Socket socket = null;
            try {
                socket = new Socket("127.0.0.1", 1500);

                System.out.println("Client online");
                Scanner in = new Scanner(System.in);
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                try {
                    while (true) {

                        String text = in.nextLine();
                        output.writeObject(text);

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        buttonToAboutClinic.setOnAction(event -> {
            String d = "The Aybolit Pediatrics Center was founded in February 2007, it was the first in the Republic of Kazakhstan to open its doors as a specialized children's medical center with outpatient care.\n" +
                    "\n" +
                    "Today the Aybolit Center for Pediatrics can proudly say that our team continues to work successfully and develop, now our team consists of 70 doctors, 20 mid-level personnel.\n Every year, our specialists go through seminars, every five years they successfully confirm their statuses - state certification.\n We have doctors of the highest category, candidates of medical sciences, doctors of medical sciences, and many of them are second-generation doctors. \nThere are doctors who have been awarded the Health Care Excellence badge. \nThe average experience of our specialists is 30 years, we can be entrusted with the most expensive.\n" +
                    "AA-4 Series License No. 0106427";
            JOptionPane.showMessageDialog(null, d, "About clinic", JOptionPane.PLAIN_MESSAGE);

        });
        buttonToContacts.setOnAction(event -> {
            String d = "Clinic number: 234-42-22\n Director's phone number: 87777777777";
            JOptionPane.showMessageDialog(null, d, "Contacts", JOptionPane.PLAIN_MESSAGE);

        });
        buttonToServices.setOnAction(event -> {
            change(buttonToServices, "ser");
        });
        buttonToHome.setOnAction(event -> {
            change(buttonToHome, "ta");
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