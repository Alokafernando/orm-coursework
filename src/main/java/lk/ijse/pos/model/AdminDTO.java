package lk.ijse.pos.model;

public class AdminDTO {

    private String username;
    private String password;

    public AdminDTO() {}

    public AdminDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    @Override
    public String toString() {
        return "AdminDTO [username=" + username + ", password=" + password + "]";
    }
}
