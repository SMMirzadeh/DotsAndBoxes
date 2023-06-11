package User;

import java.util.Random;

public class User {

    private int id;
    private String UserName;
    private String password;
    private String lastName;
    private UserColor userColor;

    public User(){

        Random random = new Random();

        this.id = random.nextInt(1000000,9999999);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserColor(UserColor userColor) {
        this.userColor = userColor;
    }

    public UserColor getUserColor() {
        return userColor;
    }
}
