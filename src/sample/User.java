package sample;

public abstract class  User {
    private String name;
    private String surname;
    private String login;
    private String password;
    private String answer;
    private String question;
    private String city;
    private String gender;
    private String role;
    public User(){
    }

    public User(String name, String surname, String login, String password, String answer, String question, int number, String city, String gender, String role) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.answer = answer;
        this.question = question;
        this.city = city;
        this.gender = gender;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }



    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

