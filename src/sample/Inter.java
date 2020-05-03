package sample;

import javafx.scene.control.Button;

import java.sql.Connection;
import java.sql.SQLException;

public interface Inter {
    public Connection getDbConnection() throws ClassNotFoundException, SQLException;
    public void change (Button button, String url);
}
