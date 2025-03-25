
package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.AdminBO;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SettingController implements Initializable  {

    @FXML
    private Button PasswordVisibilityBtn;

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
    void addUser(ActionEvent event) {
        // Add logic to add a new user
    }


    @FXML
    void deleteUser(ActionEvent event) {
        // Logic to delete a user
    }

    @FXML
    void searchUser(ActionEvent event) {
        // Logic to search for a user
    }

    @FXML
    void showCurrentAdminUsername(ActionEvent event) {
        // Logic to show the current admin username
    }


    @FXML
    void toggleUserPasswordVisibility(ActionEvent event) {
//currentPasswordField.isVisible() ||

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
    void updateUser(ActionEvent event) {
        // Add logic to update a user
    }

    @FXML
    void updateUsername(ActionEvent event) {
        String currentUsername = currentUsernameField.getText();
        String newUsername = newUsernameField.getText();

        txtNewPasswordText.setDisable(true);
        txtConfirmPasswordText.setDisable(true);

        String usernameRegex = "^(?!.*[_.]{2})[a-zA-Z0-9][a-zA-Z0-9_.]{3,28}[a-zA-Z0-9]$";

        if (currentUsername.isEmpty()) {
            usernameErrorLabel.setText("Current username cannot be empty!");
            return;
        }

        if (newUsername.isEmpty()) {
            usernameErrorLabel.setText("New username cannot be empty!");
            return;
        }

        if (!newUsername.matches(usernameRegex)) {
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
}
