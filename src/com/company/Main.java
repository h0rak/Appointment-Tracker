package com.company;

import utilities.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Locale;
import java.util.Objects;

// TODO Must be able to translate to french/english @ Main or Login controller?

public class Main extends Application {

    public static void main(String[] args) {
//         Locale.setDefault(new Locale("fr"));
        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/LoginScreen.fxml")));
        stage.setTitle("Login");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
