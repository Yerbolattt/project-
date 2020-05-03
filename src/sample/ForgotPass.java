package sample;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import animation.Shake;
import com.mysql.jdbc.ResultSetRow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.stage.Stage;

import javax.swing.*;

import static sample.DBmanager.*;


public class ForgotPass {
    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + sample.DBmanager.dbhost + ":"
                + sample.DBmanager.dbport + "/" + dbname;
        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString
                , dbuser, dbpass);
        return dbConnection;
    }

    @FXML
    private ImageView im;

    @FXML
    private Button buttonToCheck;

    @FXML
    private Button buttonToBack;

    @FXML
    private TextField texttologininrecover;

    @FXML
    private TextField texttoAnswerInRecov;

    @FXML
    private Label labelToQuestion;

    @FXML
    private Button buttonToCheck1;

    @FXML
    void initialize() {
        buttonToCheck.setOnAction(event -> {

            String a="", q="";
            String login = texttologininrecover.getText();
            if (!texttologininrecover.getText().equals("")) {
                try {
                    a=  logind(login);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else{
                String d = "Enter your login";
                JOptionPane.showMessageDialog(null, d, "Error", JOptionPane.PLAIN_MESSAGE);
            }

        });
        buttonToCheck1.setOnAction(event -> {
            String a="", q="";
            String login = texttologininrecover.getText();
            if (!texttoAnswerInRecov.getText().equals("")) {
                try {
                    a=  logind(login);
                    q=loginds(login);
                    String d="";
                    try {
                        d= givPass(q,a);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, d, "Everything went well", JOptionPane.PLAIN_MESSAGE);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else{
                String d = "Enter your answer";
                JOptionPane.showMessageDialog(null, d, "Error", JOptionPane.PLAIN_MESSAGE);
            }


        });
        buttonToBack.setOnAction(event -> {
            change(buttonToBack, "sample");
        });
    }

    private String logind(String login) throws ClassNotFoundException, SQLException {
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
        ResultSet resultSet = statement.executeQuery("SELECT *from account");
        while (resultSet.next()) {
            String log = resultSet.getString(4);
            if (login.equals(log) == true) {
                q = resultSet.getString(6);
                a = texttoAnswerInRecov.getText();
                labelToQuestion.setText(q);
                labelToQuestion.setVisible(true);
                texttoAnswerInRecov.setVisible(true);
                buttonToCheck1.setVisible(true);
                buttonToCheck.setVisible(false);
                u++;
            }

        }if(u==0){
            Shake userLogAnim = new Shake(texttologininrecover);
            userLogAnim.playAnim();
        }
        return a;
    }

    private String loginds(String login) throws ClassNotFoundException, SQLException {
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
        ResultSet resultSet = statement.executeQuery("SELECT *from account");
        while (resultSet.next()) {
            String log = resultSet.getString(4);
            if (login.equals(log) == true) {
                q = resultSet.getString(6);
                a = texttoAnswerInRecov.getText();

            }
        }
        return q;
    }
    private String givPass (String q, String a) throws ClassNotFoundException, SQLException {
       /* Class.forName("com.mysql.jdbc.Driver");
        String connectionString = "jdbc:mysql://" + dbhost + ":"
                + dbport + "/" + dbname;
        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString
                , dbuser, dbpass);

        */
        FileReader read = null;
        try {
            read = new FileReader("C:\\Users\\ONE\\Desktop\\Java\\ttt.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner in = new Scanner(read);
        ArrayList<String> db = new ArrayList<>();
        while (in.hasNext()) {
            db.add(in.next());
        }
        try {
            read.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Connection dbConnection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String connectionString = "jdbc:mysql://" + db.get(0) + ":"
                    + db.get(1) + "/" + db.get(3);
            dbConnection = DriverManager.getConnection(connectionString
                    , db.get(2), "");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        Statement statement = dbConnection.createStatement();
        int u = 0;
        String p = "Incorrect answer";
        ResultSet resultSet = statement.executeQuery("SELECT *from account");
        while (resultSet.next()) {
            String ques = resultSet.getString(6);
            String anns = resultSet.getString(7);
            if (a.equals(anns) == true && q.equals(ques) == true) {
                p = "Your password is: " + resultSet.getString(5);
                u++;
            }
        }
        if(u != 0) {
            return p;
        } else
            return p;
    }


    public void change (Button button, String url){
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
        /*
         if(u!=0){
           d = "Your password is: "+givPass(q,a);

        }
        else{
             d="The surname you entered does not exist" + "\n" + "Try again";
            texttologininrecover.setText("");
        }
        JOptionPane.showMessageDialog(null, d, "Everything went well", JOptionPane.PLAIN_MESSAGE);
    }
         */
}

