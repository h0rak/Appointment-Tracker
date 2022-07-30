package com.company;

import utilities.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;
import java.util.Locale;

/** This is the main class.
 * The main class contains the main method.
 */
public class Main extends Application {

    /** This is the main method.
     * The main method is the first method that is called when your run your Java program.
     * @param args args are an array of Strings passed to the main method
     */
    public static void main(String[] args) {
//        Locale.setDefault(new Locale("fr"));
        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();
    }

    /** The start method.
     * The start method sets the initial screen as the LoginScreen.fxml
     * @param stage The stage that is loaded and displayed
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/LoginScreen.fxml")));
        stage.setTitle("Login");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
