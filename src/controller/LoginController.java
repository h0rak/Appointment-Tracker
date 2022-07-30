package controller;

import DAO.DBUsers;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Users;
import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/** The LoginController class.
 * This class gives functionality to the Login Screen.
 */
public class LoginController implements Initializable {

    public TextField usernameInput;
    public PasswordField passwordInput;
    public Label usernameLabel;
    public Label passwordLabel;
    public Label zoneLabel;
    public Button resetButton;
    public Button loginButton;
    private boolean matchExists = false;

    /** The localeLanguageSetter method.
     * This method is used to translate English to French based on the user's locale.
     */
    private void localeLanguageSetter() {
        if (Locale.getDefault().getLanguage().equals("fr")){
            ResourceBundle rb = ResourceBundle.getBundle("utilities/RB", Locale.getDefault());
            usernameLabel.setText(rb.getString("Username") + ": ");
            passwordLabel.setText(rb.getString("Password") + ": ");
            resetButton.setText(rb.getString("Reset"));
            loginButton.setText(rb.getString("Login"));
        }
    }

    /** The onActionReset method.
     * This method clears the username and password fields.
     * @param actionEvent the event is the selection of the reset button
     */
    public void onActionReset(ActionEvent actionEvent) {
        usernameInput.clear();
        passwordInput.clear();
    }

    /** THe onActionLogin method.
     * This method verifies the username and password combination before changing scenes.
     * @param actionEvent the event is the selection of the login button
     */
    public void onActionLogin(ActionEvent actionEvent) throws IOException {
        String userName = usernameInput.getText();
        String userPassword = passwordInput.getText();
        ObservableList<Users> allUsersList = DBUsers.getAllUsers();
        for (Users u : allUsersList){
           if(u.getUserName().equals(userName) && u.getUserPassword().equals(userPassword)){
                matchExists = true;
                break;
            }
        }
        if (matchExists){
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.hide();
            appendLoginAttempts();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AppointmentScreen.fxml")));
            Scene scene = new Scene(root);
            stage.setTitle("Appointments");
            stage.setScene(scene);
            stage.show();
        }
        else {
            if(Objects.equals(userName, "") && Objects.equals(userPassword, "")) {
                appendLoginAttempts();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                if (Locale.getDefault().getLanguage().equals("fr")){
                    ResourceBundle rb = ResourceBundle.getBundle("utilities/RB", Locale.getDefault());
                    alert.setContentText(rb.getString("Please"));
                    alert.showAndWait();
                }
                else {
                    alert.setTitle("Error");
                    alert.setContentText("Please enter a username / password.");
                    alert.showAndWait();
                }
            }
            else {
                appendLoginAttempts();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                if (Locale.getDefault().getLanguage().equals("fr")){
                    ResourceBundle rb = ResourceBundle.getBundle("utilities/RB", Locale.getDefault());
                    alert.setContentText(rb.getString("Invalid"));
                    alert.showAndWait();
                }
                else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Invalid username / password combination.");
                    alert.showAndWait();
                }
            }
        }
    }

    private void appendLoginAttempts() {
        String result;
        if (matchExists){
            result = "SUCCESSFUL";
        }
        else {
            result = "FAILED";
        }
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(new File("login_activity.txt"), true));
            pw.append("Login Attempt: ").append(result).append(", User: ").append(usernameInput.getText()).append(", Date / Time: ").append(String.valueOf(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))).append(".\n");
            pw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** The initialize method.
     * This method sets all the necessary data for the screen.
     * @param url the url is default in the initialize method
     * @param resourceBundle  the resourceBundle is default in the initialize method
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        localeLanguageSetter();
        zoneLabel.setText(ZoneId.systemDefault().toString());
    }
}