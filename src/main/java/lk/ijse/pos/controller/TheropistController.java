package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

public class TheropistController {

    @FXML
    private Button btnAddTheropist;

    @FXML
    private Button btnDeleteTheropist;

    @FXML
    private Button btnUpdateTheropist;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colStatuts;

    @FXML
    private TableColumn<?, ?> colTherapistId;

    @FXML
    private RadioButton rbActive;

    @FXML
    private RadioButton rbInActive;

    @FXML
    private ToggleGroup statusBtnGroup;

    @FXML
    private TableView<?> tblTheropist;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txttherapistId;

    @FXML
    void btnAddTheropistOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteTheropistOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateTheropistOnAction(ActionEvent event) {

    }

    @FXML
    void tblTheropistOnAction(MouseEvent event) {

    }

}
