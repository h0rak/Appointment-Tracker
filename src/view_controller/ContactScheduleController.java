package view_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;

public class ContactScheduleController {

    @FXML
    private RadioButton apptScreenRadioButton;

    @FXML
    private ComboBox<?> contactComboBox;

    @FXML
    private TableView<?> contactScheduleTableView;

    @FXML
    private RadioButton contactScreenRadioButton;

    @FXML
    private RadioButton custScreenRadioButton;

    @FXML
    private Button resetTableButton;

    @FXML
    void onActionContactScreen(ActionEvent event) {

    }

    @FXML
    void onActionCustomerScreen(ActionEvent event) {

    }

    @FXML
    void onActionFilterContact(ActionEvent event) {

    }

    @FXML
    void onActionResetTable(ActionEvent event) {

    }

}
