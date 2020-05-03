package sample;
import java.sql.*;
public class DBHandl extends DBmanager{
    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://"+ dbhost + ":"
                + dbport + "/" + dbname;
        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString
                , dbuser, dbpass);
        return dbConnection;
    }
    public void signUpUser(Users users){
        String insert = "INSERT INTO " + Const.USER_TABLE + " (" +
                Const.USER_ID + "," + Const.USER_NAME + "," + Const.USER_SURNAME +","+
                Const.USER_LOGIN +"," + Const.USER_PASSWORD +"," +Const.USER_QUESTION  + ","+ Const.USER_ANSWER + "," + Const.USER_GENDER
                + "," + Const.USER_NUMBER +"," + Const.USER_CITY + "," + Const.ROLE + ")"  +
                "VALUES(null, ?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, users.getName());
            preparedStatement.setString(2, users.getSurname());
            preparedStatement.setString(3, users.getLogin());
            preparedStatement.setString(4, users.getPassword());
            preparedStatement.setString(5, users.getQuestion());
            preparedStatement.setString(6, users.getAnswer());
            preparedStatement.setString(7, users.getGender());
            preparedStatement.setString(8, String.valueOf(users.getNumber()));
            preparedStatement.setString(9, users.getCity());
            preparedStatement.setString(10, users.getRole());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet  getUser(User user){
        ResultSet resultSet = null;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USER_LOGIN + "=? AND " + Const.USER_PASSWORD + "=?";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
