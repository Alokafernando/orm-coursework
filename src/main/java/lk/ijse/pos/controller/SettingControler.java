package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.UserBO;

import java.io.IOException;
import java.net.URL;
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

    UserBO userBo = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOType.USER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        blockTabs();
    }

    public SettingControler() throws IOException {
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
    void toggleUserPasswordVisibility(ActionEvent event) {

    }

    @FXML
    void updateCredentials(ActionEvent event) {

    }

    @FXML
    void updateUser(ActionEvent event) {

    }

    private void blockTabs(){
        boolean status = userBo.status();
        if(status == true){
            adminSettings.setDisable(true);
            if (adminSettings.isSelected()){
                new Alert(Alert.AlertType.ERROR, "user can't access to admin settings", ButtonType.OK).show();
            }
        }else {
            adminSettings.setDisable(false);
        }
    }

}
