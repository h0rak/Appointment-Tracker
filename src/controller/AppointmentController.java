package controller;

import DAO.DBAppointments;
import com.company.Main;
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
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class AppointmentController implements Initializable {

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

    public static boolean firstTime = true;

    @FXML
    void onActionAppointmentScreen(ActionEvent event) {
        return;
    }

    @FXML
    void onActionCustomerScreen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/CustomerScreen.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Customers");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionReportScreen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/ReportScreen.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Reports");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionFilterAll(ActionEvent event) {
        appointmentTableView.setItems(DBAppointments.getAllAppointments());
    }

    @FXML
    void onActionFilterMonth(ActionEvent event) {
        appointmentTableView.setItems(appointmentsThisMonth());
    }

    @FXML
    void onActionFilterWeek(ActionEvent event) {
        appointmentTableView.setItems(appointmentsThisWeek());
    }

    @FXML
    void onActionAddAppointment(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AddAppointmentScreen.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Appointment");
        stage.setScene(scene);
        stage.show();
    }

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

    @FXML
    void onActionDeleteAppointment(ActionEvent event) {

        Appointments appointmentToDelete = appointmentTableView.getSelectionModel().getSelectedItem();
        int appointmentId = appointmentToDelete.getAppointmentId();
        String appointmentType = appointmentToDelete.getAppointmentType();

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

    private ObservableList<Appointments> appointmentsThisMonth(){
        ObservableList<Appointments> appointmentsThisMonthList = FXCollections.observableArrayList();
        ObservableList<Appointments> allAppointmentsList = DBAppointments.getAllAppointments();
        LocalDateTime now = LocalDateTime.now();
        for (Appointments a : allAppointmentsList){
            if (a.getStartTime().toLocalDateTime().getMonth() == LocalDateTime.now().getMonth() && a.getStartTime().toLocalDateTime().getYear() == LocalDateTime.now().getYear()) {
                appointmentsThisMonthList.add(a);
            }
        }
        return appointmentsThisMonthList;
    }

    private ObservableList<Appointments> appointmentsThisWeek(){
            ObservableList<Appointments> appointmentsThisWeekList = FXCollections.observableArrayList();
            ObservableList<Appointments> allAppointmentsList = DBAppointments.getAllAppointments();
            LocalDateTime now = LocalDateTime.now();

            for (Appointments a : allAppointmentsList){
                if (a.getStartTime().toLocalDateTime().isAfter(now) && a.getStartTime().toLocalDateTime().isBefore(now.plusDays(7))){
                    appointmentsThisWeekList.add(a);
                }
            }
            return appointmentsThisWeekList;
    }

    private void appointmentAlert(){
        if (firstTime){
            ObservableList<Appointments> allAppointmentsList = DBAppointments.getAllAppointments();
            for (Appointments a : allAppointmentsList){
                if (LocalDateTime.now().isBefore(a.getStartTime().toLocalDateTime()) && LocalDateTime.now().plusMinutes(15).isAfter(a.getStartTime().toLocalDateTime())){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alert");
                    alert.setContentText("There's an appointment within the next 15 minutes!\n\n" +
                            "Appointment ID: " + a.getAppointmentId() + ", Date: " + a.getStartTime().toLocalDateTime().toLocalDate() + ", Time: " + a.getStartTime().toLocalDateTime().toLocalTime() + "-" + a.getEndTime().toLocalDateTime().toLocalTime());
                    alert.showAndWait();
                    firstTime = false;
                    break;
                } else if (LocalDateTime.now().isAfter(a.getStartTime().toLocalDateTime()) && LocalDateTime.now().isBefore(a.getEndTime().toLocalDateTime())) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alert");
                    alert.setContentText("There's an appointment taking place now.\n\n" +
                            "Appointment ID: " + a.getAppointmentId() + ", Date: " + a.getStartTime().toLocalDateTime().toLocalDate() + ", Time: " + a.getStartTime().toLocalDateTime().toLocalTime() + "-" + a.getEndTime().toLocalDateTime().toLocalTime());
                    alert.showAndWait();
                    firstTime = false;
                    break;
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alert");
                    alert.setContentText("There are no upcoming appointments scheduled.\n");
                    alert.showAndWait();
                    firstTime = false;
                    break;
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentTableView.setItems(DBAppointments.getAllAppointments());
        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        appointmentTitleCol.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        appointmentDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        appointmentLocationCol.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
        appointmentTypeCol.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        startTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTimeDisplay"));
        endTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTimeDisplay"));
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        contactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        appointmentAlert();
    }
}
