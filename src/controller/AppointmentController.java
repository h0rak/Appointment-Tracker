package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointments;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AppointmentController implements Initializable {

    @FXML
    private TableView<Appointments> appointmentTableView;

    @FXML
    void onActionAddAppointment(ActionEvent event) {

    }

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
    void onActionDeleteAppointment(ActionEvent event) {

    }

    @FXML
    void onActionFilterAll(ActionEvent event) {

    }

    @FXML
    void onActionFilterMonth(ActionEvent event) {

    }

    @FXML
    void onActionFilterWeek(ActionEvent event) {

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
    void onActionUpdateAppointment(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        apptTableView.setItems(Appointments.get); // getAllapts doesn't exist yet in the db appts

    }
}
