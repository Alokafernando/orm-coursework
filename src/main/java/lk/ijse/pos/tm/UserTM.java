package lk.ijse.pos.tm;

public class UserTM {
    private String username;
    private String password;

    UserTM() {}

    UserTM(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    @Override
    public String toString() {
        return "UserTM{" + "username=" + username + ", password=" + password + '}';
    }
}
