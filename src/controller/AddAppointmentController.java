package controller;

import DAO.DBAppointments;
import DAO.DBContacts;
import DAO.DBCustomers;
import DAO.DBUsers;
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
import model.Divisions;
import model.Users;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
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
        try{
            String aTitle = appointmentTitleField.getText();
            String aDescription = appointmentDescriptionField.getText();
            String aLocation = appointmentLocationField.getText();
            String aType = appointmentTypeField.getText();
            Timestamp aStart = Timestamp.valueOf(LocalDateTime.of(datePickerWidget.getValue(),startTimeComboBox.getValue()));
            Timestamp aEnd = Timestamp.valueOf(LocalDateTime.of(datePickerWidget.getValue(),endTimeComboBox.getValue()));
            int aCustomerId = customerComboBox.getSelectionModel().getSelectedItem().getCustomerId();
            int aUserId = DBUsers.getFakeUserId(); //TODO - Fix how the userId is gathered
            int aContactId = contactComboBox.getSelectionModel().getSelectedItem().getContactId();

            DBAppointments.AddAppointment(aTitle, aDescription, aLocation, aType, aStart, aEnd, aCustomerId, aUserId, aContactId);

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AppointmentScreen.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Appointments");
            stage.setScene(scene);
            stage.show();
        }
        catch (NumberFormatException | IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        LocalTime start = LocalTime.of(8, 0);
        LocalTime end = LocalTime.of(22, 0);

        // this was from mark
        LocalDateTime startLdt = LocalDateTime.of(LocalDate.now(),start);
        ZonedDateTime startZdtFromEst = startLdt.atZone(ZoneId.of("America/New_York"));
        ZonedDateTime startZdtToLocal = startZdtFromEst.withZoneSameInstant(ZoneId.systemDefault());
        start = startZdtToLocal.toLocalTime();
        // this is me trying end time
        LocalDateTime endLdt = LocalDateTime.of(LocalDate.now(),end);
        ZonedDateTime endZdtFromEst = endLdt.atZone(ZoneId.of("America/New_York"));
        ZonedDateTime endZdtToLocal = endZdtFromEst.withZoneSameInstant(ZoneId.systemDefault());
        end = endZdtToLocal.toLocalTime();

//      This sets the 15-minute intervals in the Start and End Time Combo Boxes
        while(start.isBefore(end.plusMinutes(1))){
            startTimeComboBox.getItems().add(start);
            endTimeComboBox.getItems().add(start);
            start = start.plusMinutes(15);
        }

        LocalDateTime now = LocalDateTime.now();
        datePickerWidget.setValue(now.toLocalDate());
        LocalTime time = LocalTime.of(7,0);

        startTimeComboBox.setVisibleRowCount(5);
        endTimeComboBox.setVisibleRowCount(5);

        customerComboBox.setItems(DBCustomers.getAllCustomers());
        customerComboBox.setVisibleRowCount(5);

        contactComboBox.setItems(DBContacts.getAllContacts());
        contactComboBox.setVisibleRowCount(5);
    }
}
