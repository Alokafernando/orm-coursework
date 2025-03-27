package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class DashBoardController {

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnPatient;

    @FXML
    private Button btnPayment;

    @FXML
    private Button btnSession;

    @FXML
    private Button btnSetting;

    @FXML
    private Button btnTherapists;

    @FXML
    private Button btnTherapyProgram;

    @FXML
    private ScrollPane contentPanel;

    @FXML
    private Label txtActiveTherapist;

    @FXML
    private Label txtSessions;

    @FXML
    private Label txtTotalPatient;

    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/OptionSelector.fxml"));
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);

    }

    @FXML
    void btnPatientOnAction(ActionEvent event) {

    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) {

    }

    @FXML
    void btnSessionOnAction(ActionEvent event) {

    }

    @FXML
    void btnSettingOnAction(ActionEvent event) throws IOException {
        getFilePath( "/view/Setting.fxml");
    }

    @FXML
    void btnTherapistsOnAction(ActionEvent event) throws IOException {
        getFilePath("/view/Therapist.fxml");
    }

    @FXML
    void btnTherapyProgramOnAction(ActionEvent event) {

    }

    @FXML
    void txtActiveTherapistOnAction(MouseEvent event) {

    }

    @FXML
    void txtSessionOnAction(MouseEvent event) {

    }

    @FXML
    void txtTotalPatientOnAction(MouseEvent event) {

    }

//    private void getFilePath(String filePath) throws IOException {
//        contentPanel.getChildrenUnmodifiable().clear();
//        Parent root = FXMLLoader.load(getClass().getResource(filePath));
//        contentPanel.setContent(root);
//
//    }

    private void getFilePath(String filePath) throws IOException {

        System.out.println("Loading FXML file from path: " + filePath);

        Node content = contentPanel.getContent();

        if (content != null && content instanceof Pane) {
            Pane pane = (Pane) content;
            pane.getChildren().clear();
        }
        Parent root = FXMLLoader.load(getClass().getResource(filePath));

        contentPanel.setContent(root);
    }

}
