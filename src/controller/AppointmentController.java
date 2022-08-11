package controller;

import DAO.DBAppointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

/** The AppointmentController class.
 * This class gives functionality to the Appointment Screen.
 */
public class AppointmentController implements Initializable {

    private static boolean firstTime = true;

    @FXML
    private TableView<Appointments> appointmentTableView;

    @FXML
    private TableColumn<Appointments, Integer> appointmentIdCol;

    @FXML
    private TableColumn<Appointments, String> appointmentTitleCol;

    @FXML
    private TableColumn<Appointments, String> appointmentDescriptionCol;

    @FXML
    private TableColumn<Appointments, String> appointmentLocationCol;

    @FXML
    private TableColumn<Appointments, String> appointmentTypeCol;

    @FXML
    private TableColumn<Appointments, Timestamp> startTimeCol;

    @FXML
    private TableColumn<Appointments, Timestamp> endTimeCol;

    @FXML
    private TableColumn<Appointments, Integer> customerIdCol;

    @FXML
    private TableColumn<Appointments, Integer> userIdCol;

    @FXML
    private TableColumn<Appointments, Integer> contactIdCol;

    /** The onActionAppointmentScreen method.
     * This method is purposefully left blank.
     * @param event the event is the selection of the Appointment Screen radio button
     */
    @FXML
    void onActionAppointmentScreen(ActionEvent event) {
        return;
    }

    /** The onActionCustomerScreen method.
     * This method directs the user to the Customer Screen.
     * @param event the event is the selection of the Customer Screen radio button
     */
    @FXML
    void onActionCustomerScreen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/CustomerScreen.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Customers");
        stage.setScene(scene);
        stage.show();
    }

    /** The onActionReportScreen method.
     * This method directs the user to the Report Screen.
     * @param event the event is the selection of the Report Screen radio button
     */
    @FXML
    void onActionReportScreen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/ReportScreen.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Reports");
        stage.setScene(scene);
        stage.show();
    }

    /** The onActionFilterAll method.
     * This method populates all appointments into the appointmentTableView
     * @param event the event is the selection of the all radio button
     */
    @FXML
    void onActionFilterAll(ActionEvent event) {
        appointmentTableView.setItems(DBAppointments.getAllAppointments());
    }

    /** The onActionFilterMonth method.
     * This method populates all appointments of the current month into the appointmentTableView
     * @param event  the event is the selection of the month radio button
     */
    @FXML
    void onActionFilterMonth(ActionEvent event) {
        appointmentTableView.setItems(appointmentsThisMonth());
    }

    /** The onActionFilterWeek method.
     * This method populates all appointments within the next 7 days into the appointmentTableView
     * @param event  the event is the selection of the week radio button
     */
    @FXML
    void onActionFilterWeek(ActionEvent event) {
        appointmentTableView.setItems(appointmentsThisWeek());
    }

    /** The onActionAddAppointment method.
     * This method directs the user to the AddAppointment Screen.
     * @param actionEvent the actionEvent is the selection of the Add button
     */
    @FXML
    void onActionAddAppointment(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AddAppointmentScreen.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Appointment");
        stage.setScene(scene);
        stage.show();
    }

    /** The onActionUpdateAppointment method.
     * This method directs the user to the UpdateAppointment Screen.
     * @param actionEvent the actionEvent is the selection of the Update button
     */
    @FXML
    void onActionUpdateAppointment(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/UpdateAppointmentScreen.fxml"));
            loader.load();

            UpdateAppointmentController uac = loader.getController();
            uac.SendAppointment(appointmentTableView.getSelectionModel().getSelectedItem());

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setTitle("Update Appointment");
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select an appointment to update.");
            alert.showAndWait();
        }
    }

    /** The onActionDeleteAppointment method.
     * This method deletes a selected appointment from the appointmentTableView
     * @param event the event is the selection of the delete button
     */
    @FXML
    void onActionDeleteAppointment(ActionEvent event) {
        Appointments appointmentToDelete = appointmentTableView.getSelectionModel().getSelectedItem();
        if (appointmentToDelete == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select an appointment to delete.");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you wish to delete this appointment?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                DBAppointments.DeleteAppointment(appointmentToDelete.getAppointmentId());
                appointmentTableView.setItems(DBAppointments.getAllAppointments());
                int appointmentId = appointmentToDelete.getAppointmentId();
                String appointmentType = appointmentToDelete.getAppointmentType();
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Appointment Cancelled");
                alert2.setContentText("Appointment with ID " + appointmentId + " and type \"" + appointmentType + "\" was successfully cancelled.");
                alert2.showAndWait();
            }
            else {
                appointmentTableView.getSelectionModel().clearSelection();
            }
        }
    }

    /** The appointmentsThisMonth method.
     * This method scans all appointments to make a list of appointments this month.
     * @return returned is a list of appointments this month
     */
    private ObservableList<Appointments> appointmentsThisMonth(){
        ObservableList<Appointments> appointmentsThisMonthList = FXCollections.observableArrayList();
        ObservableList<Appointments> allAppointmentsList = DBAppointments.getAllAppointments();
        LocalDateTime now = LocalDateTime.now();
        for (Appointments a : allAppointmentsList){
            if (a.getStartTime().getMonth() == LocalDateTime.now().getMonth() && a.getStartTime().getYear() == LocalDateTime.now().getYear()) {
                appointmentsThisMonthList.add(a);
            }
        }
        return appointmentsThisMonthList;
    }

    /** The appointmentsThisWeek method.
     * This method scans all appointments to make a list of appointments in the next 7 days.
     * @return returned is a list of appointments in the next seven days
     */
    private ObservableList<Appointments> appointmentsThisWeek(){
            ObservableList<Appointments> appointmentsThisWeekList = FXCollections.observableArrayList();
            ObservableList<Appointments> allAppointmentsList = DBAppointments.getAllAppointments();
            LocalDateTime now = LocalDateTime.now();

            for (Appointments a : allAppointmentsList){
                if (a.getStartTime().isAfter(now) && a.getStartTime().isBefore(now.plusDays(7))){
                    appointmentsThisWeekList.add(a);
                }
            }
            return appointmentsThisWeekList;
    }

    /** The appointmentAlert method.
     * This method alerts the user to possible upcoming appointments upon login.
     */
    private void appointmentAlert(){
        ObservableList<Appointments> allAppointmentsList = DBAppointments.getAllAppointments();
        ObservableList<Appointments> upcomingAppointmentsList = FXCollections.observableArrayList();
        ObservableList<Appointments> currentAppointmentsList = FXCollections.observableArrayList();
        for (Appointments a : allAppointmentsList) {
            if (LocalDateTime.now().isBefore(a.getStartTime()) && LocalDateTime.now().plusMinutes(15).isAfter(a.getStartTime())) {
                upcomingAppointmentsList.add(a);
            } else if (LocalDateTime.now().isAfter(a.getStartTime()) && LocalDateTime.now().isBefore(a.getEndTime())) {
                currentAppointmentsList.add(a);
            }
        }
        if (!upcomingAppointmentsList.isEmpty() && firstTime){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            for (Appointments a : upcomingAppointmentsList) {
                alert.setContentText("There's an appointment within the next 15 minutes!\n\n" +
                        "Appointment ID: " + a.getAppointmentId() + ", Date: " + a.getStartTime().toLocalDate() + ", Time: " + a.getStartTime().toLocalTime() + "-" + a.getEndTime().toLocalTime());
            }
            alert.showAndWait();
            firstTime = false;
        } else if (!currentAppointmentsList.isEmpty() && firstTime) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            for (Appointments a : currentAppointmentsList) {
                alert.setContentText("There's an appointment taking place now.\n\n" +
                        "Appointment ID: " + a.getAppointmentId() + ", Date: " + a.getStartTime().toLocalDate() + ", Time: " + a.getStartTime().toLocalTime() + "-" + a.getEndTime().toLocalTime());
            }
            alert.showAndWait();
            firstTime = false;
        }
        else if (firstTime){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setContentText("There are no upcoming appointments scheduled.\n");
            alert.showAndWait();
            firstTime = false;
        }
    }

    /** The initialize method.
     * This method sets all the necessary data for the screen.
     * @param url the url is default in the initialize method
     * @param resourceBundle  the resourceBundle is default in the initialize method
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentTableView.setItems(DBAppointments.getAllAppointments());
        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        appointmentTitleCol.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        appointmentDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        appointmentLocationCol.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
        appointmentTypeCol.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
//        startTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTimeDisplay")); // original
//        endTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTimeDisplay")); // original
        startTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime")); // test
        endTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime")); // test
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        contactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        appointmentAlert();
    }
}
