package lk.ijse.pos.tm;

public class AdminTM {

    private String username;
    private String password;

    AdminTM() {}

    public AdminTM(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    @Override
    public String toString() {
        return "AdminTM{" + "username=" + username + ", password=" + password + '}';
    }

}
