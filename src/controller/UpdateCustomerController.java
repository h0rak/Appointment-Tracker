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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Countries;
import model.Customers;
import model.Divisions;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

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

    @FXML
    void onActionCountryComboBox(ActionEvent event) {
        Countries selectedCountry = countryComboBox.getSelectionModel().getSelectedItem();
        divisionComboBox.setItems(DBDivisions.getDivisionsByCountryId(selectedCountry.getCountryId()));
    }

    @FXML
    void onActionCancel(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/CustomerScreen.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Customers");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionSave(ActionEvent event) {
        try{
            String cId = customerIdField.getText(); // String or int???
            String cName = customerNameField.getText();
            String cAddress = customerAddressField.getText();
            String cPostal = customerPostalCodeField.getText();
            String cPhone = customerPhoneNumberField.getText();
            Divisions comboBoxSelection = divisionComboBox.getSelectionModel().getSelectedItem();
            int cDivision = comboBoxSelection.getDivisionId();

            DBCustomers.UpdateCustomer(Integer.parseInt(cId), cName, cAddress, cPostal, cPhone, cDivision);

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/CustomerScreen.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Customers");
            stage.setScene(scene);
            stage.show();
        }
        catch (NumberFormatException | IOException e){
            e.printStackTrace();
        }
    }

    public void SendCustomer(Customers customer) {
        customerIdField.setText(String.valueOf(customer.getCustomerId())); // id name address postal phone division
        customerNameField.setText(String.valueOf(customer.getCustomerName()));
        customerAddressField.setText(String.valueOf(customer.getCustomerAddress()));
        customerPostalCodeField.setText(String.valueOf(customer.getCustomerPostalCode()));
        customerPhoneNumberField.setText(String.valueOf(customer.getCustomerPhone()));

        countryComboBox.setItems(DBCountries.getAllCountries());
        countryComboBox.setValue(DBCountries.getCustomerCountryFromDivisionId(customer.getCustomerDivisionId()));
        divisionComboBox.setItems(DBDivisions.getDivisionsByCountryId(countryComboBox.getValue().getCountryId()));
        divisionComboBox.setValue(DBDivisions.getDivisionNameFromDivisionId(customer.getCustomerDivisionId()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryComboBox.setItems(DBCountries.getAllCountries()); // SHOULD I DO THIS HERE?
        countryComboBox.setVisibleRowCount(3);
        divisionComboBox.setVisibleRowCount(4);
    }
}
