package controller;

import DAO.DBAppointments;
import DAO.DBContacts;
import DAO.DBCustomers;
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
import model.Contacts;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Objects;
import java.util.ResourceBundle;

public class ReportController implements Initializable {

    private final ObservableList<String> months = FXCollections.observableArrayList("JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER");

    public ToggleGroup tg1;

    @FXML
    private TableView<Appointments> contactScheduleTableView;

    @FXML
    private TableColumn<Appointments, String> appointmentDescriptionCol;

    @FXML
    private TableColumn<Appointments, Integer> appointmentIdCol;

    @FXML
    private TableColumn<Appointments, String> appointmentLocationCol;

    @FXML
    private TableColumn<Appointments, String> appointmentTitleCol;

    @FXML
    private TableColumn<Appointments, String> appointmentTypeCol;

    @FXML
    private TableColumn<Appointments, Integer> contactIdCol;

    @FXML
    private TableColumn<Appointments, Integer> customerIdCol;

    @FXML
    private TableColumn<Appointments, Timestamp> endTimeCol;

    @FXML
    private TableColumn<Appointments, Timestamp> startTimeCol;

    @FXML
    private Label totalCustomersLabel;

    @FXML
    private Label typeMonthTotalLabel;

    @FXML
    private TableColumn<Appointments, Integer> userIdCol;

    @FXML
    private ComboBox<String> monthComboBox;

    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    private ComboBox<Contacts> contactComboBox;


    @FXML
    void onActionAppointmentScreen(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AppointmentScreen.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Appointments");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionCustomerScreen(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/CustomerScreen.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Customers");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionFilterContactAppointments(ActionEvent event) {
        Contacts selectedContact = contactComboBox.getSelectionModel().getSelectedItem();
        contactScheduleTableView.setItems(DBAppointments.getAppointmentsByContactId(selectedContact.getContactId()));
    }

    @FXML
    void onActionReportScreen(ActionEvent event) {
        return;
    }

    @FXML
    void onActionTypeMonthTotal(ActionEvent event) {
        String typeToSearch = typeComboBox.getSelectionModel().getSelectedItem();
        Month monthToSearch = Month.valueOf(monthComboBox.getSelectionModel().getSelectedItem());
        int totalHours = 0;
        System.out.println(monthComboBox.getSelectionModel().getSelectedItem());

        for (Appointments a: DBAppointments.getAllAppointments()){
            if (a.getAppointmentType().equals(typeToSearch) && a.getStartTime().toLocalDateTime().getMonth().equals(monthToSearch)) {
                totalHours += a.getStartTime().toLocalDateTime().compareTo(a.getEndTime().toLocalDateTime());
                System.out.println(a.getStartTime().toLocalDateTime().getMonth());
            }
        }
        typeMonthTotalLabel.setText(String.valueOf(Math.abs(totalHours)) + " hours total"); // setup method to get hour total from database
        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactComboBox.setItems(DBContacts.getAllContacts());
        contactScheduleTableView.setItems(DBAppointments.getAllAppointments());
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
        typeComboBox.setItems(DBAppointments.getAllTypes());
        typeComboBox.setVisibleRowCount(4);
        monthComboBox.setItems(months);
        monthComboBox.setVisibleRowCount(4);
        totalCustomersLabel.setText(DBCustomers.getTotalCustomers());
    }

}
