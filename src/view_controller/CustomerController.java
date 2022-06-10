package view_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerController {

    @FXML
    private Button addCustomerButton;

    @FXML
    private RadioButton apptScreenRadioButton;

    @FXML
    private RadioButton contactScreenRadioButton;

    @FXML
    private RadioButton custScreenRadioButton;

    @FXML
    private TableView<?> customerTableView;

    @FXML
    private Button deleteCustomertButton;

    @FXML
    private Button updateCustomerButton;

    @FXML
    void onActionAddCustomer(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view_controller/AddCustomerScreen.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 400, 400);
        stage.setTitle("Add Customer");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionAppointmentScreen(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view_controller/AppointmentScreen.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 700);
        stage.setTitle("Appointments");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionContactScreen(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view_controller/ContactScheduleScreen.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 700);
        stage.setTitle("Contact Schedule");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionCustomerScreen(ActionEvent event) {

    }

    @FXML
    void onActionDeleteCustomer(ActionEvent event) {

    }

    @FXML
    void onActionUpdateCustomer(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view_controller/UpdateCustomerScreen.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 400, 400);
        stage.setTitle("Update Customer");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

}
