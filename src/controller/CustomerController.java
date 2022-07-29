package controller;

import DAO.DBAppointments;
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
import model.Customers;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

/** The CustomerController class.
 * This class gives functionality to the Customer Screen.
 */
public class CustomerController implements Initializable {

    @FXML
    private TableView<Customers> customerTableView;

    @FXML
    private TableColumn<Customers, String> customerAddressCol;

    @FXML
    private TableColumn<Customers, String> customerDivisionCol;

    @FXML
    private TableColumn<Customers, Integer> customerIdCol;

    @FXML
    private TableColumn<Customers, String> customerNameCol;

    @FXML
    private TableColumn<Customers, String> customerPhoneCol;

    @FXML
    private TableColumn<Customers, Integer> customerPostalCodeCol;

    /** The onActionAddCustomer method.
     * This method directs the user to the Add Customer Screen.
     * @param actionEvent the event is the selection of the Add button
     */
    @FXML
    void onActionAddCustomer(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AddCustomerScreen.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Customer");
        stage.setScene(scene);
        stage.show();
    }

    /** The onActionAppointmentScreen method.
     * This method directs the user to the Appointment Screen.
     * @param actionEvent the event is the selection of the Appointment Screen radio button
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

    /** The onActionReportScreen method.
     * This method directs the user to the Report Screen.
     * @param actionEvent the event is the selection of the Report Screen radio button
     */
    @FXML
    void onActionReportScreen(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/ReportScreen.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Reports");
        stage.setScene(scene);
        stage.show();
    }

    /** The onActionCustomerScreen method.
     * This method is purposefully left blank.
     * @param event the event is the selection of the Customer Screen radio button
     */
    @FXML
    void onActionCustomerScreen(ActionEvent event) {
        return;
    }

    /** The onActionDeleteCustomer method.
     * This method deletes a selected Customer from the customerTableView.
     * @param event the even is the selection of the delete button
     */
    @FXML
    void onActionDeleteCustomer(ActionEvent event) throws NullPointerException{
        try {
            Customers customerToDelete = customerTableView.getSelectionModel().getSelectedItem();
            ObservableList<Appointments> allAppointmentsList = DBAppointments.getAllAppointments();
            ObservableList<Appointments> customerAppointments = FXCollections.observableArrayList();
            int customerId = customerToDelete.getCustomerId();
            String customerName = customerToDelete.getCustomerName();

            for (Appointments a : allAppointmentsList){
                if (a.getCustomerId() == customerId){
                    customerAppointments.add(a);
                }
            }
            if (!customerAppointments.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Customers with existing appointments cannot be deleted.");
                alert.showAndWait();
            }
            else {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you wish to delete this customer?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    DBCustomers.DeleteCustomer(customerToDelete.getCustomerId());
                    customerTableView.setItems(DBCustomers.getAllCustomers());
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Delete Successful");
                    alert2.setContentText("Customer with ID " + customerId + ", \"" + customerName + "\" was successfully deleted.");
                    alert2.showAndWait();
                }
                else {
                    customerTableView.getSelectionModel().clearSelection();
                }
            }
        }
        catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a customer to delete.");
            alert.showAndWait();
        }
    }

    /** THe onActionUpdateCustomer method.
     * This method sends a selected customer to the UpdateCustomer Screen.
     * @param actionEvent the event is the selection of the update button
     */
    @FXML
    void onActionUpdateCustomer(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/UpdateCustomerScreen.fxml"));
            loader.load();

            UpdateCustomerController ucc = loader.getController();
            ucc.SendCustomer(customerTableView.getSelectionModel().getSelectedItem());

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setTitle("Update Customer");
            stage.setScene(new Scene(scene));
            stage.show();
        }
        catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a customer to update.");
            alert.showAndWait();
        }
    }

    /** The initialize method.
     * This method sets all the necessary data for the screen.
     * @param url the url is default in the initialize method
     * @param resourceBundle  the resourceBundle is default in the initialize method
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerTableView.setItems(DBCustomers.getAllCustomers());
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerAddressCol.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        customerPostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
        customerPhoneCol.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        customerDivisionCol.setCellValueFactory(new PropertyValueFactory<>("customerDivisionId"));
    }
}
