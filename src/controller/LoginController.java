package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

//     TODO somewhere in initialize or maybe override i have to check the user's local time zone
//     TODO login activity txt file

    public TextField usernameInput;
    public PasswordField passwordInput;

    public void onActionReset(ActionEvent actionEvent) {
        usernameInput.clear();
        passwordInput.clear();
    }

    public void onActionLogin(ActionEvent actionEvent) throws IOException {

        // get text fields

        // check database for combination of username password

        // if .. correct

        // print valid login to a file

        // 15 minute alert (grab user element)

        // change screens

        // if .. not correct

        // print invalid login to a file

        // pop-up alert "bad login"

        // Code repository : PrintWriter

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AppointmentScreen.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Appointments");
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}