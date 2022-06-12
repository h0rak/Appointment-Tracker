package com.company;

import DAO.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// have to be able to translate to french/english main or login controller?


public class Main extends Application {

    public static void main(String[] args) {


	// write your code here
        JDBC.openConnection();
        // Locale.setDefault(new Locale("fr"));
        launch(args);
        JDBC.closeConnection();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginScreen.fxml"));
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 450, 250));
        stage.setResizable(false);
        stage.show();
    }
}
