//Filename: RegisterController.java
//Author(s): Allen Cedric Domingo

package com.example.demo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//RegisterController class - Code written by @Allen Cedric Domingo
public class RegisterController implements Initializable {

    @FXML
    private Button registration_register_btn;

    @FXML
    private Button registration_cancel_btn;

    @FXML
    private TextField registration_username_textfield;

    @FXML
    private TextField registration_password_textfield;

    @FXML
    private Label welcomeText;
    private Stage stage;
    private Scene scene;
    private Parent root;

    //cancelButton method - Code written by @Allen Cedric Domingo
    public void cancelButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //registerButton method - Code written by @Allen Cedric Domingo
    public void registerButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //initialize method - Code written by @Allen Cedric Domingo
    public void initialize (URL location, ResourceBundle resources){

        registration_register_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!registration_username_textfield.getText().trim().isEmpty() && !registration_password_textfield.getText().trim().isEmpty()){
                    Driver.userSignUp(event, registration_username_textfield.getText(), registration_password_textfield.getText());
                }else {
                    System.out.println("Please fill in all information");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information to Register!");
                    alert.show();
                }
            }
        });

        registration_cancel_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Driver.shiftScene(event, "login.fxml", "Log in!", null);
            }
        });
    }

}