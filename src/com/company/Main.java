package com.company;

import utilities.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;
import java.util.Locale;

public class Main extends Application {

//    TODO + ERROR CHECKING WITH CUSTOMERS
//    TODO + ERROR CHECKING WITH APPOINTMENTS (SCHEDULING OVERLAPPING APPOINTMENTS)
//    TODO + 2 LAMBDA EXPRESSIONS
//    TODO + JAVADOC
//    TODO + README.TXT

    public static void main(String[] args) {
        Locale.setDefault(new Locale("fr"));
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
