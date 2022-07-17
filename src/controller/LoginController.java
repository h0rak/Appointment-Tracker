package controller;

import DAO.DBUsers;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Users;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

// TODO somewhere in initialize or maybe override i have to check the user's local time zone
// TODO login activity txt file (PrintWriter)
// TODO 15-minute alert - grab user element

    public TextField usernameInput;
    public PasswordField passwordInput;

    public void onActionReset(ActionEvent actionEvent) {
        usernameInput.clear();
        passwordInput.clear();
    }

    public void onActionLogin(ActionEvent actionEvent) throws IOException {

        String userName = usernameInput.getText();
        String userPassword = passwordInput.getText();
        boolean matchExists = false;

        ObservableList<Users> allUsersList = DBUsers.getAllUsers();
        for (Users u : allUsersList){

           if(u.getUserName().equals(userName) && u.getUserPassword().equals(userPassword)){
                matchExists = true;
                break;
            }

        }
        if (matchExists){
            // change screens
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AppointmentScreen.fxml")));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Appointments");
            stage.setScene(scene);
            stage.show();
        }
        else {
            if(Objects.equals(userName, "") && Objects.equals(userPassword, "")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Please enter a username / password.");
                alert.showAndWait();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Invalid username / password combination.");
                alert.showAndWait();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}