package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class OptionSelectorController {

    @FXML
    private AnchorPane adminPanel;

    @FXML
    private Button btnAdmin;

    @FXML
    private Button btnUser;

    @FXML
    private AnchorPane selectionPanel;

    @FXML
    void navigateToAdminLoginPanel(ActionEvent event) throws IOException {
        getFilePath("/view/Adminlogin.fxml");
    }

    @FXML
    void navigateToUserLoginPanel(ActionEvent event) throws IOException {
        getFilePath("/view/UserLogin.fxml");
    }

    private void getFilePath(String filePath) throws IOException {
        selectionPanel.getChildren().clear();
        AnchorPane pane = FXMLLoader.load(getClass().getResource(filePath));
        selectionPanel.getChildren().add(pane);
    }

}
