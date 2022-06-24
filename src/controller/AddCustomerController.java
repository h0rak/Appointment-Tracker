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
import model.Divisions;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {

    @FXML
    private TextField customerIdField;

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
        /*if(selectedCountry.getCountryId() == 1){
            divisionComboBox.getSelectionModel().clearSelection();
            divisionComboBox.setItems(DBDivisions.getUSDivisions(selectedCountry));
            divisionComboBox.setPromptText("State");
        }
        else if(selectedCountry.getCountryId() == 2){
            divisionComboBox.getSelectionModel().clearSelection();
            divisionComboBox.setItems(DBDivisions.g());
            divisionComboBox.setPromptText("Province");
        }
        else if(selectedCountry.getCountryId() == 3){
            divisionComboBox.getSelectionModel().clearSelection();
            divisionComboBox.setItems(DBDivisions.getCANDivisions());
            divisionComboBox.setPromptText("Province");
        }
        else {
            return;
        }*/
    }



    @FXML
    void onActionSave(ActionEvent event) {
        try{
            String cName = customerNameField.getText();
            String cAddress = customerAddressField.getText();
            String cPostal = customerPostalCodeField.getText();
            String cPhone = customerPhoneNumberField.getText();
//            String cDivision = String.valueOf(divisionComboBox.getSelectionModel().getSelectedItem());
//            int cDivision = divisionComboBox.getSelectionModel().getSelectedItem();
            Divisions comboBoxSelection = divisionComboBox.getSelectionModel().getSelectedItem();
            int cDivision = comboBoxSelection.getDivisionId();
            System.out.println(cDivision);

            DBCustomers.AddCustomer(cName, cAddress, cPostal, cPhone, cDivision);

        }
        catch (NumberFormatException e){
            e.printStackTrace();
        }

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryComboBox.setItems(DBCountries.getAllCountries()); // SHOULD I DO THIS HERE?
        countryComboBox.setVisibleRowCount(3);
        // divisionComboBox.setItems(DBDivisions.getAllDivisions()); // SHOULD I DO THIS HERE?
        divisionComboBox.setVisibleRowCount(4);
        // divisionComboBox.setPromptText("Choose a division..");
        // divisionComboBox.getSelectionModel().selectFirst();  THESE TWO WILL BE USED WITH MODIFY???
        // divisionComboBox.setValue("a variable not a string?");
    }
}
