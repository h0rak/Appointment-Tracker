package com.company;

import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {
	// write your code here
        JDBC.openConnection();
        JDBC.closeConnection();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view_controller/LoginScreen.fxml"));
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 450, 250));
        stage.setResizable(false);
        stage.show();
    }
}
