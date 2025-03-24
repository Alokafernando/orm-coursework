package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.AdminBO;
import lk.ijse.pos.model.AdminDTO;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SettingControler implements Initializable {

    @FXML
    private Button PasswordVisibilityBtn;

    @FXML
    private Button addUserBtn;

    @FXML
    private Tab adminSettings;

    @FXML
    private Button clearFormBtn;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label credentialsStatusLabel;

    @FXML
    private PasswordField currentPasswordField;

    @FXML
    private TextField currentUsernameField;

    @FXML
    private Button deleteUserBtn;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private TextField newUsernameField;

    @FXML
    private Label passwordErrorLabel;

    @FXML
    private Button searchUserBtn;

    @FXML
    private AnchorPane settingPanel;

    @FXML
    private Button toggleUserPasswordBtn;

    @FXML
    private Button updateCredentialsBtn;

    @FXML
    private Button updateUserBtn;

    @FXML
    private TextField userEmailField;

    @FXML
    private TextField userIdField;

    @FXML
    private Label userOperationStatusLabel;

    @FXML
    private PasswordField userPasswordField;

    @FXML
    private TextField userPhoneField;

    @FXML
    private TextField userRoleField;

    @FXML
    private Label userSearchStatusLabel;

    @FXML
    private Tab userSettings;

    @FXML
    private TextField userUsernameField;

    @FXML
    private Label usernameErrorLabel;

    AdminBO adminBO = (AdminBO) BOFactory.getInstance().getBO(BOFactory.BOType.ADMIN);

    public SettingControler() throws IOException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            List<String> adminNames = adminBO.getAdminNames();

            if (adminNames != null && !adminNames.isEmpty()) {
                currentUsernameField.setText(adminNames.get(0));
            } else {
                currentUsernameField.setText("Admin not found!");
            }
        } catch (IOException e) {
            e.printStackTrace();
            currentUsernameField.setText("Error fetching admin!");
        }

    }

    @FXML
    void PasswordVisibility(ActionEvent event) {

    }

    @FXML
    void addUser(ActionEvent event) {

    }

    @FXML
    void clearUserForm(ActionEvent event) {

    }

    @FXML
    void deleteUser(ActionEvent event) {

    }

    @FXML
    void searchUser(ActionEvent event) {

    }

    @FXML
    void showCurrentAdminUsername(ActionEvent event) {

    }

    @FXML
    void toggleUserPasswordVisibility(ActionEvent event) {

    }

    @FXML
    void updateCredentials(ActionEvent event) {

    }

    @FXML
    void updateUser(ActionEvent event) {

    }

}
