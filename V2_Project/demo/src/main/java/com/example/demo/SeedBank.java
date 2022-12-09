//Filename: SeedBank.java
//Author(s): Allen Cedric Domingo

package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

// This is the main method for my program.
// The code for this program was developed solely by:
// • Allen Cedric Domingo
//
// Thank you to my professor Jendy Wu, for providing us guidance, and pushing us to overcome all obstacles.

public class SeedBank extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SeedBank.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1366, 768);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}