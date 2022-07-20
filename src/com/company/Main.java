package com.company;

import utilities.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Locale;
import java.util.Objects;

// TODO FRENCH / ENGLISH: MAIN OR LOGIN?

public class Main extends Application {

    public boolean firstTime = true;

    public static void main(String[] args) {
//         Locale.setDefault(new Locale("fr", "FR"));
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
