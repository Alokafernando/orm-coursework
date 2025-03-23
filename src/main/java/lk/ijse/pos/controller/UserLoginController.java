package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.UserBO;
import lk.ijse.pos.model.UserDTO;

import java.io.IOException;

public class UserLoginController {

    @FXML
    private AnchorPane adminPanel;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnTogglePassword;

    @FXML
    private CheckBox chkRememberMe;

    @FXML
    private ImageView imgEyeIcon;

    @FXML
    private Label lblError;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtPasswordText;

    @FXML
    private TextField txtUsername;

    UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOType.USER);

    public UserLoginController() throws IOException {
    }

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        System.out.println("username -> " + username);
        System.out.println("password -> " + password);

        UserDTO userDTO = userBO.findCredential(username);

        if (userDTO != null && username.equals(userDTO.getUsername()) && password.equals(userDTO.getPassword())) {
            loadScene(event, "/view/Dashboard.fxml");
        } else {
            lblError.setText("Invalid credentials. Please try again.");
            lblError.setVisible(true);
        }
    }

    private void loadScene(ActionEvent event, String fxmlPath) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }


    @FXML
    void navigateToSelectPanel(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/OptionSelector.fxml"));
        Parent root = ((Node) event.getSource()).getScene().getRoot();
        if (root instanceof AnchorPane) {
            ((AnchorPane) root).getChildren().setAll(anchorPane);
        }
    }

    @FXML
    void togglePasswordVisibility(ActionEvent event) {
        if (txtPassword.isVisible()) {
            txtPassword.setVisible(false);
            txtPasswordText.setText(txtPassword.getText());
            txtPasswordText.setVisible(true);

            ImageView imgEye = new ImageView(new Image(getClass().getResourceAsStream("/assets/images/closed-eye.png")));
            imgEye.setFitHeight(20);
            imgEye.setFitWidth(20);
            btnTogglePassword.setGraphic(imgEye);

        } else {
            txtPasswordText.setVisible(false);
            txtPassword.setText(txtPasswordText.getText());
            txtPassword.setVisible(true);

            ImageView imgEye = new ImageView(new Image(getClass().getResourceAsStream("/assets/images/open-eye.png")));
            imgEye.setFitHeight(20);
            imgEye.setFitWidth(20);
            btnTogglePassword.setGraphic(imgEye);
        }

    }

}
