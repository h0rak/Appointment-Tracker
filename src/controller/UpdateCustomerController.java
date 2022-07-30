package controller;

import DAO.DBCountries;
import DAO.DBCustomers;
import DAO.DBDivisions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Countries;
import model.Customers;
import model.Divisions;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

/** The UpdateCustomerController class.
 * This class presents a form that allows the user to update an existing customer.
 */
public class UpdateCustomerController implements Initializable {

    @FXML
    private ComboBox<Countries> countryComboBox;

    @FXML
    private TextField customerAddressField;

    @FXML
    private TextField customerIdField;

    @FXML
    private TextField customerNameField;

    @FXML
    private TextField customerPhoneNumberField;

    @FXML
    private TextField customerPostalCodeField;

    @FXML
    private ComboBox<Divisions> divisionComboBox;

    /** The onActionCountryComboBox method.
     * This method sets the appropriate divisions in the divisionComboBox when a country is selected.
     * @param event the event is the choosing of a country in the combo box
     */
    @FXML
    void onActionCountryComboBox(ActionEvent event) {
        Countries selectedCountry = countryComboBox.getSelectionModel().getSelectedItem();
        divisionComboBox.setItems(DBDivisions.getDivisionsByCountryId(selectedCountry.getCountryId()));
    }

    /** The onActionCancel method.
     * This method presents an alert before discarding the UpdateCustomer form.
     * @param actionEvent the actionEvent is the clicking of the cancel button
     */
    @FXML
    void onActionCancel(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Any unsaved information will be lost. Do you wish to continue?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/CustomerScreen.fxml")));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Customers");
            stage.setScene(scene);
            stage.show();
        }
    }

    /** The onActionSave method.
     * This method allows the user to update a customer
     * @param event the event is the clicking of the update button.
     */
    @FXML
    void onActionSave(ActionEvent event) {

        try {
            int cId = Integer.parseInt(customerIdField.getText());
            String cName = customerNameField.getText();
            String cAddress = customerAddressField.getText();
            String cPostal = customerPostalCodeField.getText();
            String cPhone = customerPhoneNumberField.getText();
            Divisions comboBoxSelection = divisionComboBox.getSelectionModel().getSelectedItem();
            int cDivision = comboBoxSelection.getDivisionId();

            String errorMessage = Customers.inputChecker(cName, cAddress, cPostal, cPhone, cDivision, "");

            if (errorMessage.length() > 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Invalid input. Customer not updated. See value error(s) below.");
                alert.setContentText(errorMessage);
                alert.showAndWait();
            } else {
                DBCustomers.UpdateCustomer(cId, cName, cAddress, cPostal, cPhone, cDivision);

                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/CustomerScreen.fxml")));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setTitle("Customers");
                stage.setScene(scene);
                stage.show();
            }
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Invalid input. Customer not updated. See value error(s) below.");
            alert.setContentText("No text fields, widgets, or combo boxes may be left blank.");
            alert.showAndWait();
        }
    }

    /** The SendCustomer method.
     * This method allows the user to send a customer to another screen to update.
     * @param customer the customer param is the selected customer on the Customer Screen
     */
    public void SendCustomer(Customers customer) {
        customerIdField.setText(String.valueOf(customer.getCustomerId()));
        customerNameField.setText(String.valueOf(customer.getCustomerName()));
        customerAddressField.setText(String.valueOf(customer.getCustomerAddress()));
        customerPostalCodeField.setText(String.valueOf(customer.getCustomerPostalCode()));
        customerPhoneNumberField.setText(String.valueOf(customer.getCustomerPhone()));

        countryComboBox.setItems(DBCountries.getAllCountries());
        countryComboBox.setValue(DBCountries.getCustomerCountryFromDivisionId(customer.getCustomerDivisionId()));
        divisionComboBox.setItems(DBDivisions.getDivisionsByCountryId(countryComboBox.getValue().getCountryId()));
        divisionComboBox.setValue(DBDivisions.getDivisionNameFromDivisionId(customer.getCustomerDivisionId()));
    }

    /** The initialize method.
     * This method sets all the necessary data for the screen.
     * @param url the url is default in the initialize method
     * @param resourceBundle  the resourceBundle is default in the initialize method
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryComboBox.setItems(DBCountries.getAllCountries());
        countryComboBox.setVisibleRowCount(3);
        divisionComboBox.setVisibleRowCount(4);
    }
}
