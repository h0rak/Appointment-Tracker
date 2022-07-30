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
import model.Contacts;
import utilities.AllContactsInterface;
import utilities.CustomerSumInterface;
import utilities.JDBC;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Objects;
import java.util.ResourceBundle;

/** The ReportController class.
 * This class gives functionality to the Report Screen.
 */
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


    /** The onActionAppointmentScreen method.
     * This method directs the user to the Appointment Screen.
     * @param actionEvent the event is the selection of the Appointments radio button
     */
    @FXML
    void onActionAppointmentScreen(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AppointmentScreen.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Appointments");
        stage.setScene(scene);
        stage.show();
    }

    /** The onActionCustomerScreen method.
     * This method directs the user to the Customer Screen.
     * @param actionEvent the even is the selection of the Customers radio button
     */
    @FXML
    void onActionCustomerScreen(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/CustomerScreen.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Customers");
        stage.setScene(scene);
        stage.show();
    }

    /** The onActionFilterContactAppointments method.
     * This method sets the items for the contactScheduleTableView to be filtered by contact.
     * @param event the event is the selection of a contact from the contactComboBox
     */
    @FXML
    void onActionFilterContactAppointments(ActionEvent event) {
        Contacts selectedContact = contactComboBox.getSelectionModel().getSelectedItem();
        contactScheduleTableView.setItems(DBAppointments.getAppointmentsByContactId(selectedContact.getContactId()));
    }

    /** The onActionReportScreen method.
     * This method is purposefully left blank.
     * @param event the event is the selection of the Reports radio button
     */
    @FXML
    void onActionReportScreen(ActionEvent event) {
        return;
    }

    /** The onActionTypeMonthTotal method.
     * This method produces a sum based off of the filters set by the Month and Type combo boxes and set a label.
     * @param event the event is the selection of the total button
     */
    @FXML
    void onActionTypeMonthTotal(ActionEvent event) throws NullPointerException {
        String typeToSearch = typeComboBox.getSelectionModel().getSelectedItem();
        Month monthToSearch = Month.valueOf(monthComboBox.getSelectionModel().getSelectedItem());
        int total = 0;
        for (Appointments a: DBAppointments.getAllAppointments()){
            if (a.getAppointmentType().equals(typeToSearch) && a.getStartTime().toLocalDateTime().getMonth().equals(monthToSearch)) {
                total += 1;
            }
        }
        typeMonthTotalLabel.setText(String.valueOf(total) + " " + typeToSearch + " appointment(s) in " + monthToSearch + ".");
    }

    /** The customerCount Lambda Expression.
     * This expression totals the customers in the database.
     * @return returned is the total number of customer objects
     */
    CustomerSumInterface customerCount = () -> {
        String sql = "SELECT COUNT(*) FROM client_schedule.customers;";
        String totalCustomers = null;
        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                totalCustomers = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalCustomers;
    };

    /** The contactList Lambda Expression.
     * This expression sets a list of contacts' names as a string.
     * @return returned is the list of contacts
     */
    AllContactsInterface contactList = () -> {
        ObservableList<Contacts> allContactsList = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * FROM client_schedule.contacts;";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String contactEmail = rs.getString("Email");

                Contacts c = new Contacts(contactId, contactName, contactEmail);
                allContactsList.add(c);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return allContactsList;
    };

    /** The initialize method.
     * This method sets all the necessary data for the screen.
     * @param url the url is default in the initialize method
     * @param resourceBundle  the resourceBundle is default in the initialize method
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        contactComboBox.setItems(DBContacts.getAllContacts());
        contactComboBox.setItems(contactList.getAllContacts());
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
        typeComboBox.setValue(DBAppointments.getAllTypes().get(0));
        typeComboBox.setVisibleRowCount(4);
        monthComboBox.setItems(months);
        monthComboBox.setValue(LocalDateTime.now().getMonth().name());
        monthComboBox.setVisibleRowCount(4);
//        totalCustomersLabel.setText(DBCustomers.getTotalCustomers());
        totalCustomersLabel.setText(customerCount.sumOfCustomers());
    }

}
