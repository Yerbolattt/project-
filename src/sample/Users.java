package sample;

public class Users extends User {
    private int number;

    public Users() {

    }


    public Users(String name, String surname, String login, String password, String answer, String question, int number, String city, String gender, String role) {
        super(name, surname, login, password, answer, question, number, city, gender, role);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
