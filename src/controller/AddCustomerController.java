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

public class AddCustomerController implements Initializable {
    
    @FXML
    private TextField customerNameField;

    @FXML
    private TextField customerAddressField;

    @FXML
    private TextField customerPhoneNumberField;

    @FXML
    private TextField customerPostalCodeField;

    @FXML
    private ComboBox<Countries> countryComboBox;

    @FXML
    private ComboBox<Divisions> divisionComboBox;

    @FXML
    void onActionCountryComboBox(ActionEvent event) {
        Countries selectedCountry = countryComboBox.getSelectionModel().getSelectedItem();
        divisionComboBox.setItems(DBDivisions.getDivisionsByCountryId(selectedCountry.getCountryId()));
    }

    @FXML
    void onActionSave(ActionEvent event) {
        try{
            String cName = customerNameField.getText();
            String cAddress = customerAddressField.getText();
            String cPostal = customerPostalCodeField.getText();
            String cPhone = customerPhoneNumberField.getText();
            Divisions comboBoxSelection = divisionComboBox.getSelectionModel().getSelectedItem();
            int cDivision = comboBoxSelection.getDivisionId();

            String errorMessage = Customers.inputChecker(cName, cAddress, cPostal, cPhone, cDivision, "");

            if (errorMessage.length() > 0){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Invalid input. Customer not saved. See value error(s) below.");
                alert.setContentText(errorMessage);
                alert.showAndWait();
            }
            else {
                DBCustomers.AddCustomer(cName, cAddress, cPostal, cPhone, cDivision);

                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/CustomerScreen.fxml")));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setTitle("Customers");
                stage.setScene(scene);
                stage.show();
            }
        } catch (NullPointerException | IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Invalid input. Customer not saved. See value error(s) below.");
            alert.setContentText("No text fields, widgets, or combo boxes may be left blank.");
            alert.showAndWait();
        }
    }

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryComboBox.setItems(DBCountries.getAllCountries());
        countryComboBox.setVisibleRowCount(3);
        divisionComboBox.setVisibleRowCount(4);
    }
}
