
package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.AdminBO;
import lk.ijse.pos.bo.custom.UserBO;
import lk.ijse.pos.model.UserDTO;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class SettingController implements Initializable  {

    @FXML
    private Button PasswordVisibilityBtn;

    @FXML
    private TextField UserPasswordTextField;

    @FXML
    private Button addUserBtn;

    @FXML
    private Tab adminSettings;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label credentialsStatusLabel;

    @FXML
    private Label credentialsStatusLabel1;

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
    private TextField txtConfirmPasswordText;

    @FXML
    private TextField txtNewPasswordText;

    @FXML
    private Button updatePasswordBtn;

    @FXML
    private Button updateUserBtn;

    @FXML
    private Button updateUserNameBtn1;

    @FXML
    private TextField userIdField;

    @FXML
    private Label userOperationStatusLabel;

    @FXML
    private PasswordField userPasswordField;

    @FXML
    private Label userSearchStatusLabel;

    @FXML
    private Tab userSettings;

    @FXML
    private TextField userUsernameField;

    @FXML
    private Label usernameErrorLabel;


    private final AdminBO adminBO = (AdminBO) BOFactory.getInstance().getBO(BOFactory.BOType.ADMIN);
    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOType.USER);

    public SettingController() throws IOException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            showCurrentUserName();
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void refreshPage() {
        showCurrentUserName();
        newUsernameField.setText("");

        txtNewPasswordText.setVisible(false);
        txtConfirmPasswordText.setVisible(false);

        currentPasswordField.setText("");
        newPasswordField.setText("");
        confirmPasswordField.setText("");

        usernameErrorLabel.setText("");
        passwordErrorLabel.setText("");

        userIdField.setText("");

        userUsernameField.setText("");
        UserPasswordTextField.setVisible(false);
        userPasswordField.setText("");
    }

    private void showCurrentUserName() {
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

        if (newPasswordField.isVisible() || confirmPasswordField.isVisible()) {
            newPasswordField.setVisible(false);
            confirmPasswordField.setVisible(false);

            txtNewPasswordText.setText(newPasswordField.getText());
            txtConfirmPasswordText.setText(confirmPasswordField.getText());

            txtNewPasswordText.setVisible(true);
            txtConfirmPasswordText.setVisible(true);
        } else {
            txtNewPasswordText.setVisible(false);
            txtConfirmPasswordText.setVisible(false);

            newPasswordField.setText(txtNewPasswordText.getText());
            confirmPasswordField.setText(txtConfirmPasswordText.getText());

            newPasswordField.setVisible(true);
            confirmPasswordField.setVisible(true);
        }
    }

    @FXML
    void addUser(ActionEvent event) throws IOException {

        String userId = userIdField.getText().trim();
        String userName = userUsernameField.getText().trim();
        String password = userPasswordField.getText().trim();

        if (userId.isEmpty() || userName.isEmpty() || password.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields are required!").show();
            return;
        }

        String idPattern = "^[0-9]{3,6}$";
        String usernamePattern = "^(?!.*[_.]{2})[a-zA-Z0-9][a-zA-Z0-9_.]{3,28}[a-zA-Z0-9]$";
        String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";


        boolean isvalidId = userId.matches(idPattern);
        boolean isvalidName = userName.matches(usernamePattern);
        boolean isvalidPassword = password.matches(passwordPattern);

        if (!isvalidId){
            new Alert(Alert.AlertType.ERROR, "Invalid ID!").show();
        } else if (!isvalidName) {
            new Alert(Alert.AlertType.ERROR, "Invalid Name!").show();
        } else if (!isvalidPassword) {
            new Alert(Alert.AlertType.ERROR, "Invalid Password!").show();
        }

        if (isvalidId && isvalidName && isvalidPassword) {
            UserDTO userDTO = new UserDTO(userId, userName, password);
            userBO.save(userDTO);
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Successfully added user!").show();
        }else{
            new Alert(Alert.AlertType.ERROR, "user added failed!").show();
        }
    }


    @FXML
    void deleteUser(ActionEvent event) {
        String userId = userIdField.getText().trim();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.get() == ButtonType.YES) {
            userBO.delete(userId);
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Successfully deleted!").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Deletion failed!").show();
        }
    }

    @FXML
    void searchUser(ActionEvent event) throws IOException {
       String userId = userIdField.getText();
       String userName = currentUsernameField.getText();
       String userPassword = txtNewPasswordText.getText();

        UserDTO userDTO = userBO.findCredential(userId);
        if (userDTO != null){
            userUsernameField.setText(userDTO.getUsername());
            UserPasswordTextField.setVisible(false);
            userPasswordField.setText(userDTO.getPassword());
        }else{
            new Alert(Alert.AlertType.ERROR, "user can not find").show();
            refreshPage();
        }
    }

    @FXML
    void showCurrentAdminUsername(ActionEvent event) {
        // empty
    }


    @FXML
    void toggleUserPasswordVisibility(ActionEvent event) {

        if(userPasswordField.isVisible()){
            userPasswordField.setVisible(false);
            UserPasswordTextField.setText(userPasswordField.getText());
            UserPasswordTextField.setVisible(true);
        }else{
            UserPasswordTextField.setVisible(false);
            userPasswordField.setText(UserPasswordTextField.getText());
            userPasswordField.setVisible(true);
        }

    }


    @FXML
    void updatePassword(ActionEvent event) throws IOException {

        String currentUsername = currentUsernameField.getText();
        String currentPassword = currentPasswordField.getText();

        String newPassword = newPasswordField.isVisible() ? newPasswordField.getText() : txtNewPasswordText.getText();
        String confirmPassword = confirmPasswordField.isVisible() ? confirmPasswordField.getText() : txtConfirmPasswordText.getText();

        if (!validatePasswordFields(currentUsername, currentPassword, newPassword, confirmPassword)) {
            return;
        }

        boolean isUpdated = adminBO.updatePassword(currentUsername, currentPassword, newPassword);
        if (isUpdated) {
            refreshPage();
            passwordErrorLabel.setText("Password Updated Successfully!");
        } else {
            passwordErrorLabel.setText("Failed to Update Password!");
        }

    }

    private boolean validatePasswordFields(String username, String currentPassword, String newPassword, String confirmPassword) {
        String passwordRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

        if (username.isEmpty()) {
            usernameErrorLabel.setText("Username cannot be empty!");
            return false;
        }
        if (currentPassword.isEmpty()) {
            passwordErrorLabel.setText("Current password is empty!");
            return false;
        }
        if (!newPassword.matches(passwordRegex)) {
            passwordErrorLabel.setText("Password must be at least 8 characters, include uppercase, lowercase, number, and special character!");
            return false;
        }
        if (!newPassword.equals(confirmPassword)) {
            passwordErrorLabel.setText("New Password and Confirm Password do not match!");
            return false;
        }
        return true;
    }

    @FXML
    void updateUser(ActionEvent event) throws IOException {
        String userId = userIdField.getText().trim();
        String userName = userUsernameField.getText().trim();
        String password = userPasswordField.getText().trim();

        if (userId.isEmpty() || userName.isEmpty() || password.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields are required!").show();
            return;
        }

        String idPattern = "^[a-zA-Z0-9]{3,6}$";
        String usernamePattern = "^(?!.*[_.]{2})[a-zA-Z0-9][a-zA-Z0-9_.]{3,28}[a-zA-Z0-9]$";
        String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

        boolean isValidId = userId.matches(idPattern);
        boolean isValidName = userName.matches(usernamePattern);
        boolean isValidPassword = password.matches(passwordPattern);

        if (!isValidId) {
            new Alert(Alert.AlertType.ERROR, "Invalid ID!").show();
            return;
        }
        if (!isValidName) {
            new Alert(Alert.AlertType.ERROR, "Invalid Name!").show();
            return;
        }
        if (!isValidPassword) {
            new Alert(Alert.AlertType.ERROR, "Invalid Password!").show();
            return;
        }

        if (isValidId && isValidName && isValidPassword) {
            UserDTO userDTO = new UserDTO(userId, userName, password);
            userBO.update(userDTO);
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Successfully updated user!").show();
        }else{
            new Alert(Alert.AlertType.ERROR, "user updated failed!").show();
        }

    }

    @FXML
    void updateUsername(ActionEvent event) {
        String currentUsername = currentUsernameField.getText();
        String newUsername = newUsernameField.getText();

        txtNewPasswordText.setDisable(true);
        txtConfirmPasswordText.setDisable(true);

        if (currentUsername.isEmpty()) {
            usernameErrorLabel.setText("Current username cannot be empty!");
            return;
        }

        if (newUsername.isEmpty()) {
            usernameErrorLabel.setText("New username cannot be empty!");
            return;
        }

        if (!isValidUsername(newUsername)) {
            usernameErrorLabel.setText("Invalid username! Username must be 5-30 characters long, can only contain letters, numbers, underscores (_), and dots (.), but cannot start/end with a special character or have consecutive dots/underscores.");
            return;
        }

        try {
            boolean isUpdated = adminBO.updateUsername(currentUsername, newUsername);
            if (isUpdated) {
                refreshPage();
                usernameErrorLabel.setText("Username Updated Successfully!");
            } else {
                usernameErrorLabel.setText("Failed to Update Username!");
            }
        } catch (IOException e) {
            usernameErrorLabel.setText("Error Updating Username!");
            e.printStackTrace();
        }

    }

    private boolean isValidUsername(String username) {
        String usernameRegex = "^(?!.*[_.]{2})[a-zA-Z0-9][a-zA-Z0-9_.]{3,28}[a-zA-Z0-9]$";
        return username.matches(usernameRegex);
    }


}


