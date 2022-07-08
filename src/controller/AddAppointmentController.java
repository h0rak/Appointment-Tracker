package controller;

import DAO.DBContacts;
import DAO.DBCustomers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Contacts;
import model.Customers;
import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {

    @FXML
    private TextField appointmentDescriptionField;

    @FXML
    private TextField appointmentIdField;

    @FXML
    private TextField appointmentLocationField;

    @FXML
    private TextField appointmentTitleField;

    @FXML
    private TextField appointmentTypeField;

    @FXML
    private ComboBox<Contacts> contactComboBox;

    @FXML
    private ComboBox<Customers> customerComboBox;

    @FXML
    private ComboBox<LocalTime> endTimeComboBox; // <LocalTime>

    @FXML
    private ComboBox<LocalTime> startTimeComboBox; // <LocalTime>

    @FXML
    private DatePicker datePickerWidget;

    @FXML
    void onActionCancel(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AppointmentScreen.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Appointments");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionSave(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactComboBox.setItems(DBContacts.getAllContacts());
        customerComboBox.setItems(DBCustomers.getAllCustomers());

        // THIS WILL CHANGE, RIGHT??
        LocalTime start = LocalTime.of(8, 0);
        LocalTime end = LocalTime.of(21, 0);

        LocalDateTime startLDT = LocalDateTime.of(LocalDate.now(),start);
        ZonedDateTime startZDTFROMEST = startLDT.atZone(ZoneId.of("America/New_York"));
        ZonedDateTime startZDTTOLOCAL = startZDTFROMEST.withZoneSameInstant(ZoneId.systemDefault());
        start = startZDTTOLOCAL.toLocalTime();

//      This sets the 15-minute intervals in the Start and End Time Combo Boxes
        while(start.isBefore(end.plusMinutes(1))){
            startTimeComboBox.getItems().add(start);
            endTimeComboBox.getItems().add(start);
            start = start.plusMinutes(15);
        }

        LocalDateTime now = LocalDateTime.now();
        datePickerWidget.setValue(now.toLocalDate());
        LocalTime time = LocalTime.of(7,0);

        startTimeComboBox.setValue(time);
        endTimeComboBox.setValue(time.plusMinutes(15));
        startTimeComboBox.setVisibleRowCount(5);
        endTimeComboBox.setVisibleRowCount(5);
        customerComboBox.setVisibleRowCount(5);
        contactComboBox.setVisibleRowCount(5);

    }
}
