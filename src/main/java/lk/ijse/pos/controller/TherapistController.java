package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.TherapistBO;
import lk.ijse.pos.model.TherapistDTO;
import lk.ijse.pos.tm.TherapistTM;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class TherapistController implements Initializable {

    @FXML
    private Button btnAddTheropist;

    @FXML
    private Button btnDeleteTheropist;

    @FXML
    private Button btnUpdateTheropist;

    @FXML
    private TableColumn<TherapistTM, String> colContact;

    @FXML
    private TableColumn<TherapistTM, String> colEmail;

    @FXML
    private TableColumn<TherapistTM, String> colName;

    @FXML
    private TableColumn<TherapistTM, String> colStatuts;

    @FXML
    private TableColumn<TherapistTM, String> colTherapistId;

    @FXML
    private RadioButton rbActive;

    @FXML
    private RadioButton rbInActive;

    @FXML
    private ToggleGroup statusBtnGroup;

    @FXML
    private TableView<TherapistTM> tblTheropist;

    @FXML
    private ScrollPane theropistPanel;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txttherapistId;


    TherapistBO theropistBO = (TherapistBO) BOFactory.getInstance().getBO(BOFactory.BOType.THEROPIST);

    public TherapistController() throws IOException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colTherapistId.setCellValueFactory(new PropertyValueFactory<>("therapistId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colStatuts.setCellValueFactory(new PropertyValueFactory<>("statuts"));

        try{
            refresh();
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void refresh(){
        loadNextTheropistId();
        loadTableData();

        txtName.setText("");
        txtContact.setText("");
        txtEmail.setText("");
    }

    private void loadTableData() {
        String nextId = theropistBO.generateNewID();
        txttherapistId.setText(nextId);
    }

    private void loadNextTheropistId() {
        tblTheropist.getItems().clear();
        try{
            ArrayList<TherapistDTO> allTherapists = new ArrayList<>();
            for(TherapistDTO therapist : allTherapists){
                tblTheropist.getItems().add(new TherapistTM(therapist.getTheropistId(), therapist.getName(), therapist.getContact(), therapist.getEmail(), therapist.getStatus()));
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnAddTherapistOnAction(ActionEvent event) {
        String therapistId = txttherapistId.getText();
        String name = txtName.getText();
        String contact = txtContact.getText();
        String email = txtEmail.getText();


        if (name.isEmpty() || !name.matches("^[A-Za-z\\s]+$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Name! Only letters and spaces are allowed.").show();
            txtName.setStyle("-fx-border-width: 0 0 1 0; -fx-border-color: red; -fx-background-color: transparent;");
            return;
        }else{
            txtName.setStyle("-fx-border-width: 0 0 1 0; -fx-border-color: blue; -fx-background-color: transparent;");
        }

        if (contact.isEmpty() || !contact.matches("^(07[01245678])\\d{7}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Contact! Must be a 10-digit Sri Lankan number starting with 07X.").show();
            txtContact.setStyle("-fx-border-width: 0 0 1 0; -fx-border-color: red; -fx-background-color: transparent;");
            return;
        }else{
            txtContact.setStyle("-fx-border-width: 0 0 1 0; -fx-border-color: blue; -fx-background-color: transparent;");
        }

        if (email.isEmpty() || !email.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Email! Please enter a valid email address.").show();
            txtEmail.setStyle("-fx-border-width: 0 0 1 0; -fx-border-color: red; -fx-background-color: transparent;");
            return;
        }else{
            txtEmail.setStyle("-fx-border-width: 0 0 1 0; -fx-border-color: blue; -fx-background-color: transparent;");
        }

        Toggle selectedToggle = statusBtnGroup.getSelectedToggle();
        if (selectedToggle == null) {
            new Alert(Alert.AlertType.ERROR, "Please select a status!").show();
            return;
        }

        String status = ((RadioButton) selectedToggle).getText();

        TherapistDTO therapistDTO = new TherapistDTO(therapistId, name, contact, email, status);
        theropistBO.save(therapistDTO);
        refresh();
        new Alert(Alert.AlertType.INFORMATION, "Therapist added successfully").show();


    }

    @FXML
    void btnDeleteTherapistOnAction(ActionEvent event) {
        String theropistId = tblTheropist.getSelectionModel().getSelectedItem().getTheropistId();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.get() == ButtonType.YES){
            theropistBO.delete(theropistId);
            refresh();
            new Alert(Alert.AlertType.INFORMATION, "Therapist deleted successfully!").show();
        }else{
            new Alert(Alert.AlertType.INFORMATION, "Failed to delete therapist").show();
        }

    }

    @FXML
    void btnUpdateTherapistOnAction(ActionEvent event) {

    }

    @FXML
    void tblTherapistOnAction(MouseEvent event) {

    }


}


