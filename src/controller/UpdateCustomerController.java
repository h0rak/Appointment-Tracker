package controller;

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
import model.Customers;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class UpdateCustomerController implements Initializable {

    @FXML
    private ComboBox<?> countryComboBox;

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
    private ComboBox<?> divisionComboBox;

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

    }

    public void SendCustomer(Customers customer) {
        customerIdField.setText(String.valueOf(customer.getCustomerId())); // id name address postal phone division
        customerNameField.setText(String.valueOf(customer.getCustomerName()));
        customerAddressField.setText(String.valueOf(customer.getCustomerAddress()));
        customerPostalCodeField.setText(String.valueOf(customer.getCustomerPostalCode()));
        customerPhoneNumberField.setText(String.valueOf(customer.getCustomerPhone()));
/*
        countryComboBox.
        divisionComboBox.setSelectionModel();

*/
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
