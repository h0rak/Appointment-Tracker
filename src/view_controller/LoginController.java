package view_controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public TextField usernameInput;
    public PasswordField passwordInput;

    public void onActionReset(ActionEvent actionEvent) {
        usernameInput.clear();
        passwordInput.clear();
    }

    public void onActionLogin(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}