package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class UserLoginController {

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
    private TextField txtUsername;

    @FXML
    private AnchorPane userPanel;

    @FXML
    void btnLoginOnAction(ActionEvent event) {

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

    }

}
