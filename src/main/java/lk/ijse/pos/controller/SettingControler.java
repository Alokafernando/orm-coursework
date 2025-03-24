//package lk.ijse.pos.controller;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.*;
//import javafx.scene.layout.AnchorPane;
//import lk.ijse.pos.bo.BOFactory;
//import lk.ijse.pos.bo.custom.AdminBO;
//import lk.ijse.pos.model.AdminDTO;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.List;
//import java.util.ResourceBundle;
//
//public class SettingControler implements Initializable {
//
//    @FXML
//    private Button PasswordVisibilityBtn;
//
//    @FXML
//    private Button addUserBtn;
//
//    @FXML
//    private Tab adminSettings;
//
//    @FXML
//    private Button clearFormBtn;
//
//    @FXML
//    private PasswordField confirmPasswordField;
//
//    @FXML
//    private Label credentialsStatusLabel;
//
//    @FXML
//    private PasswordField currentPasswordField;
//
//    @FXML
//    private TextField currentUsernameField;
//
//    @FXML
//    private Button deleteUserBtn;
//
//    @FXML
//    private PasswordField newPasswordField;
//
//    @FXML
//    private TextField newUsernameField;
//
//    @FXML
//    private Label passwordErrorLabel;
//
//    @FXML
//    private Button searchUserBtn;
//
//    @FXML
//    private AnchorPane settingPanel;
//
//    @FXML
//    private Button toggleUserPasswordBtn;
//
//    @FXML
//    private Button updateCredentialsBtn;
//
//    @FXML
//    private Button updateUserBtn;
//
//    @FXML
//    private TextField userEmailField;
//
//    @FXML
//    private TextField userIdField;
//
//    @FXML
//    private Label userOperationStatusLabel;
//
//    @FXML
//    private PasswordField userPasswordField;
//
//    @FXML
//    private TextField userPhoneField;
//
//    @FXML
//    private TextField userRoleField;
//
//    @FXML
//    private Label userSearchStatusLabel;
//
//    @FXML
//    private Tab userSettings;
//
//    @FXML
//    private TextField userUsernameField;
//
//    @FXML
//    private Label usernameErrorLabel;
//
//    AdminBO adminBO = (AdminBO) BOFactory.getInstance().getBO(BOFactory.BOType.ADMIN);
//
//    public SettingControler() throws IOException {
//    }
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        try {
//            List<String> adminNames = adminBO.getAdminNames();
//
//            if (adminNames != null && !adminNames.isEmpty()) {
//                currentUsernameField.setText(adminNames.get(0));
//            } else {
//                currentUsernameField.setText("Admin not found!");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            currentUsernameField.setText("Error fetching admin!");
//        }
//    }
//
//    @FXML
//    void PasswordVisibility(ActionEvent event) {
//        // Toggle the password visibility
//        if (newPasswordField.getPromptText().equals("Password")) {
//            newPasswordField.setPromptText("Password (hidden)");
//            newPasswordField.setText(newPasswordField.getText());
//        } else {
//            newPasswordField.setPromptText("Password");
//            newPasswordField.setText("");
//        }
//    }
//
//    @FXML
//    void addUser(ActionEvent event) {
//        // Add user functionality (not implemented)
//    }
//
//    @FXML
//    void clearUserForm(ActionEvent event) {
//        // Clear all form fields
//        newUsernameField.clear();
//        newPasswordField.clear();
//        confirmPasswordField.clear();
//        currentPasswordField.clear();
//        currentUsernameField.clear();
//    }
//
//    @FXML
//    void deleteUser(ActionEvent event) {
//        // Delete user functionality (not implemented)
//    }
//
//    @FXML
//    void searchUser(ActionEvent event) {
//        // Search user functionality (not implemented)
//    }
//
//    @FXML
//    void showCurrentAdminUsername(ActionEvent event) {
//        // This can be used to show the current admin username
//    }
//
//    @FXML
//    void toggleUserPasswordVisibility(ActionEvent event) {
//        // Toggle password visibility for user settings
//        if (userPasswordField.isVisible()) {
//            userPasswordField.setVisible(false);
//        } else {
//            userPasswordField.setVisible(true);
//        }
//    }
//
//    @FXML
//    void updateCredentials(ActionEvent event) throws IOException {
//        // Define regular expressions for valid username and password
//        String namePatter = "^[a-zA-Z][a-zA-Z0-9_]{2,19}$";
//        String passwordPatter = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$";
//
//        // Get the input values
//        String username = newUsernameField.getText();
//        String password = newPasswordField.getText();
//        String confirmPassword = confirmPasswordField.getText();
//
//        // Check if username and password are valid
//        boolean isValidName = username.matches(namePatter);
//        boolean isValidPassword = password.matches(passwordPatter);
//
//        // Determine if the username or password has been changed
//        boolean isUsernameChanged = !username.equals(currentUsernameField.getText());
//        boolean isPasswordChanged = !password.isEmpty() && !password.equals(currentPasswordField.getText());
//
//        // Handle invalid username
//        if (isUsernameChanged && !isValidName) {
//            new Alert(Alert.AlertType.WARNING, "Invalid username! Username must start with a letter and be 3-20 characters long.").show();
//        }
//        // Handle invalid password
//        else if (isPasswordChanged && !isValidPassword) {
//            new Alert(Alert.AlertType.WARNING, "Invalid password! Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character.").show();
//        }
//        else {
//            // Handle password mismatch
//            if (isPasswordChanged && !password.equals(confirmPassword)) {
//                new Alert(Alert.AlertType.WARNING, "Passwords do not match!").show();
//            } else {
//                // If only the username is changed
//                if (isUsernameChanged && !isPasswordChanged) {
//                    // Keep the current password and update only the username
//                    AdminDTO adminDTO = new AdminDTO(username, currentPasswordField.getText());
//                    try {
//                        adminBO.update(adminDTO);  // Update only the username
//                        new Alert(Alert.AlertType.CONFIRMATION, "Admin's username changed successfully!").show();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        new Alert(Alert.AlertType.ERROR, "Error updating admin username!").show();
//                    }
//                }
//
//                // If only the password is changed
//                if (!isUsernameChanged && isPasswordChanged) {
//                    // Keep the current username and update only the password
//                    AdminDTO adminDTO = new AdminDTO(currentUsernameField.getText(), password);
//                    try {
//                        adminBO.update(adminDTO);  // Update only the password
//                        new Alert(Alert.AlertType.CONFIRMATION, "Admin's password changed successfully!").show();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        new Alert(Alert.AlertType.ERROR, "Error updating admin password!").show();
//                    }
//                }
//
//                // If both username and password are changed
//                if (isUsernameChanged && isPasswordChanged) {
//                    // Update both username and password
//                    AdminDTO adminDTO = new AdminDTO(username, password);
//                    try {
//                        adminBO.update(adminDTO);  // Update both credentials
//                        new Alert(Alert.AlertType.CONFIRMATION, "Admin's credentials changed successfully!").show();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        new Alert(Alert.AlertType.ERROR, "Error updating admin credentials!").show();
//                    }
//                }
//            }
//        }
//    }
//
//    @FXML
//    void updateUser(ActionEvent event) {
//        // Update user functionality (not implemented)
//    }
//}



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

public class SettingControler implements Initializable  {

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

    private final AdminBO adminBO = (AdminBO) BOFactory.getInstance().getBO(BOFactory.BOType.ADMIN);

    public SettingControler() throws IOException {
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
        // Add logic to handle password visibility if needed
    }

    @FXML
    void addUser(ActionEvent event) {
        // Add logic to add a new user
    }

    @FXML
    void clearUserForm(ActionEvent event) {
        // Logic to clear the user form
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
        if (userPasswordField.isVisible()) {
            txtNewPasswordText.setText(userPasswordField.getText());
            txtConfirmPasswordText.setText(confirmPasswordField.getText());
            userPasswordField.setVisible(false);
            confirmPasswordField.setVisible(false);
            txtNewPasswordText.setVisible(true);
            txtConfirmPasswordText.setVisible(true);
        } else {
            userPasswordField.setText(txtNewPasswordText.getText());
            confirmPasswordField.setText(txtConfirmPasswordText.getText());
            userPasswordField.setVisible(true);
            confirmPasswordField.setVisible(true);
            txtNewPasswordText.setVisible(false);
            txtConfirmPasswordText.setVisible(false);
        }
    }

    @FXML
    void updatePassword(ActionEvent event) {
        try {
            String currentUsername = currentUsernameField.getText();
            String currentPassword = currentPasswordField.getText();
            String newPassword = newPasswordField.getText();
            String confirmPassword = confirmPasswordField.getText();

            if (!newPassword.equals(confirmPassword)) {
                passwordErrorLabel.setText("Passwords do not match!");
                return;
            }

            boolean isUpdated = adminBO.updatePassword(currentUsername, currentPassword, newPassword);
            if (isUpdated) {
                passwordErrorLabel.setText("Password Updated Successfully!");
            } else {
                passwordErrorLabel.setText("Current password is incorrect!");
            }
        } catch (IOException e) {
            passwordErrorLabel.setText("Error Updating Password!");
            e.printStackTrace();
        }
    }

    @FXML
    void updateUser(ActionEvent event) {
        // Add logic to update a user
    }

    @FXML
    void updateUsername(ActionEvent event) {
        String currentUsername = currentUsernameField.getText();
        String newUsername = newUsernameField.getText();

        if (currentUsername.isEmpty()) {
            usernameErrorLabel.setText("Current username cannot be empty!");
            return;
        }

        if (newUsername.isEmpty()) {
            usernameErrorLabel.setText("New username cannot be empty!");
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
