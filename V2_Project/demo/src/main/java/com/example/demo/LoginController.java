//Filename: LoginController.java
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//LoginController class - Code written by @Allen Cedric Domingo and @Allen Cedric Domingo
public class LoginController implements Initializable {

    @FXML
    private Button login_login_btn;
    @FXML
    private Button login_register_btn;
    @FXML
    private TextField login_username_textfield;
    @FXML
    private TextField login_password_textfield;
    private Stage stage;
    private Scene scene;
    private Parent root;

    //SIDE NAVIGATION BAR - Code written by @Allen Cedric Domingo
    public void loginButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void registerButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("register.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    //Initialize function - Code written by @Allen Cedric Domingo
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        login_login_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Driver.userLogIn(event, login_username_textfield.getText(), login_password_textfield.getText());
            }
        });

        //Register button function - Code written by @Allen Cedric Domingo
        login_register_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Driver.shiftScene(event, "register.fxml", "Sign Up!", null);
            }
        });

    }

}