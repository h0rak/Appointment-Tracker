package controller;

import DAO.DBAppointments;
import DAO.DBContacts;
import DAO.DBCustomers;
import DAO.DBUsers;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointments;
import model.Contacts;
import model.Customers;
import model.Users;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.*;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

/** The UpdateAppointmentController class.
 * This class presents a form that allows the user to update an existing appointment.
 */
public class UpdateAppointmentController implements Initializable {

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
    private ComboBox<Users> userComboBox;

    @FXML
    private ComboBox<Customers> customerComboBox;

    @FXML
    private ComboBox<LocalTime> endTimeComboBox;

    @FXML
    private ComboBox<LocalTime> startTimeComboBox;

    @FXML
    private DatePicker datePickerWidget;

    /** The onActionCancel method.
     * This method presents an alert before discarding the UpdateAppointment form.
     * @param actionEvent the event is the selection of the cancel button
     */
    @FXML
    void onActionCancel(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Any unsaved information will be lost. Do you wish to continue?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AppointmentScreen.fxml")));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Appointments");
            stage.setScene(scene);
            stage.show();
        }
    }

    /** The onActionSave method.
     * This method allows the user to update an existing appointment.
     * @param event the event is the selection of the update button
     */
    @FXML
    void onActionSave(ActionEvent event) {
        try{
            int aId = Integer.parseInt(appointmentIdField.getText());
            String aTitle = appointmentTitleField.getText();
            String aDescription = appointmentDescriptionField.getText();
            String aLocation = appointmentLocationField.getText();
            String aType = appointmentTypeField.getText();
            LocalDateTime aStart = LocalDateTime.of(datePickerWidget.getValue(),startTimeComboBox.getValue());
            LocalDateTime aEnd = LocalDateTime.of(datePickerWidget.getValue(),endTimeComboBox.getValue());
            int aCustomerId = customerComboBox.getSelectionModel().getSelectedItem().getCustomerId();
            int aUserId = userComboBox.getSelectionModel().getSelectedItem().getUserId();
            int aContactId = contactComboBox.getSelectionModel().getSelectedItem().getContactId();

            String errorMessage = Appointments.inputChecker(aTitle, aDescription, aLocation, aType, aStart, aEnd, aCustomerId, aUserId, aContactId, "");

            if (errorMessage.length() > 0){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Invalid input. Appointment not updated. See value error(s) below.");
                alert.setContentText(errorMessage);
                alert.showAndWait();
            }
            else {
                boolean existingAppointments = false;
                ObservableList<Appointments> customersAppointments = Appointments.getCustomersAppointmentList(aCustomerId, aId);
                for (Appointments a : customersAppointments) {
                    if (aStart.isBefore(a.getStartTime()) && aEnd.isAfter(a.getStartTime())) {
                        existingAppointments = true;
                    } else if (aStart.isEqual(a.getStartTime())) {
                        existingAppointments = true;
                    } else if (aStart.isAfter(a.getStartTime()) && aStart.isBefore(a.getEndTime())) {
                        existingAppointments = true;
                    }
                }
                if (existingAppointments) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Appointment not added.");
                    alert.setContentText(" Customer has existing appointments in timeslot.");
                    alert.showAndWait();
                } else {
                    DBAppointments.UpdateAppointment(aId, aTitle, aDescription, aLocation, aType, Timestamp.valueOf(aStart), Timestamp.valueOf(aEnd), aCustomerId, aUserId, aContactId);

                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AppointmentScreen.fxml")));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setTitle("Appointments");
                    stage.setScene(scene);
                    stage.show();
                }
            }
        }
        catch (NullPointerException | IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Invalid input. Appointment not updated. See value error(s) below.");
            alert.setContentText("No text fields, widgets, or combo boxes may be left blank.");
            alert.showAndWait();
        }
    }


    /** The SendAppointment method.
     * This method allows the user to send an appointment to another screen to update.
     * @param appointment the appointment param is the selected appointment on the Appointment Screen
     */
    public void SendAppointment(Appointments appointment) {
        appointmentIdField.setText(String.valueOf(appointment.getAppointmentId()));
        appointmentTitleField.setText(String.valueOf(appointment.getAppointmentTitle()));
        appointmentDescriptionField.setText(String.valueOf(appointment.getAppointmentDescription()));
        appointmentLocationField.setText(String.valueOf(appointment.getAppointmentLocation()));
        appointmentTypeField.setText(String.valueOf(appointment.getAppointmentType()));
        datePickerWidget.setValue(appointment.getStartTime().toLocalDate());
        startTimeComboBox.setValue(appointment.getStartTime().toLocalTime()); // ORIGINAL
        endTimeComboBox.setValue(appointment.getEndTime().toLocalTime()); // ORIGINAL
        customerComboBox.setItems(DBCustomers.getAllCustomers());
        customerComboBox.setValue(DBAppointments.getCustomerByAppointmentId(appointment.getAppointmentId()));
        userComboBox.setItems(DBUsers.getAllUsers());
        userComboBox.setValue(DBAppointments.getUserByAppointmentId(appointment.getAppointmentId()));
        contactComboBox.setItems(DBContacts.getAllContacts());
        contactComboBox.setValue(DBAppointments.getContactByAppointmentId(appointment.getAppointmentId()));
    }

    /** The initialize method.
     * This method sets all the necessary data for the screen.
     * @param url the url is default in the initialize method
     * @param resourceBundle  the resourceBundle is default in the initialize method
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LocalTime start = LocalTime.of(8, 0);
        LocalTime end = LocalTime.of(22, 0);

        LocalDateTime startLdt = LocalDateTime.of(LocalDate.now(),start);
        ZonedDateTime startZdtFromEst = startLdt.atZone(ZoneId.of("America/New_York"));
        ZonedDateTime startZdtToLocal = startZdtFromEst.withZoneSameInstant(ZoneId.systemDefault());
        start = startZdtToLocal.toLocalTime();
        LocalDateTime endLdt = LocalDateTime.of(LocalDate.now(),end);
        ZonedDateTime endZdtFromEst = endLdt.atZone(ZoneId.of("America/New_York"));
        ZonedDateTime endZdtToLocal = endZdtFromEst.withZoneSameInstant(ZoneId.systemDefault());
        end = endZdtToLocal.toLocalTime();

        while(start.isBefore(end.plusMinutes(1))){
            startTimeComboBox.getItems().add(start);
            endTimeComboBox.getItems().add(start);
            start = start.plusMinutes(15);
        }

        startTimeComboBox.setVisibleRowCount(5);
        endTimeComboBox.setVisibleRowCount(5);

        customerComboBox.setItems(DBCustomers.getAllCustomers());
        customerComboBox.setVisibleRowCount(5);

        userComboBox.setItems(DBUsers.getAllUsers());
        userComboBox.setVisibleRowCount(2);

        contactComboBox.setItems(DBContacts.getAllContacts());
        contactComboBox.setVisibleRowCount(5);

    }
}
