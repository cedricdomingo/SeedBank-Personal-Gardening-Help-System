//Filename: UserSession.java
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
import java.sql.*;
import java.util.ResourceBundle;

import static com.example.demo.Driver.shiftScene;
import static com.example.demo.UserSession.username;

public class UserSettingsController implements Initializable {
    @FXML
    private Label welcomeText;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label sidebar_loggedInAs;
    @FXML
    private Label sidebar_preferredAlias;
    @FXML
    private Button sidebar_logout_btn;
    @FXML
    private Label dashboard_preferredAlias;
    @FXML
    private static TextField userSettings_newPassword_textfield;
    @FXML
    private static TextField userSettings_confirmPassword_textfield;

    //SIDE NAVIGATION BAR - Code written by @Allen Cedric Domingo
    public void goToDashboard(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goToSeedEntry(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("seedEntry.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goToSearch(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("search.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goToPlantingSchedule(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("plantingSchedule.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goToUserSettings(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("userSettings.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goToReports(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("reports.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void logoutButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

//
//    //updatePassword Method - Code written by @Allen Cedric Domingo
//    public void updatePassword() {
//
//        String newPassword = userSettings_newPassword_textfield.getText();
//        String confirmPassword = userSettings_confirmPassword_textfield.getText();
//        System.out.print(newPassword);
//        System.out.print(confirmPassword);
//        Connection connection = null;
//        PreparedStatement psInsert = null;
//        PreparedStatement psCheckUserExists = null;
//        ResultSet resultSet = null;
//
//        try {
//            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");
//            psCheckUserExists = connection.prepareStatement("SELECT * FROM RegisteredUser WHERE username = ?");
//            psCheckUserExists.setString(2, username);
//            resultSet = psCheckUserExists.executeQuery();
//
//            if (!newPassword.equals(confirmPassword)) {
//                System.out.println("User already exists!");
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setContentText("You cannot use this username.");
//                alert.show();
//            } else {
//                psInsert = connection.prepareStatement("UPDATE RegisteredUser(password) SET password = " + confirmPassword);
//                psInsert.executeUpdate();
//                System.out.println("Success!");
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setContentText("Success");
//                alert.show();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            if (resultSet != null) {
//                try {
//                    resultSet.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//
//
//            }
//
//        }



    //initialize function - Code written by @Allen Cedric Domingo
    public void initialize(URL url, ResourceBundle resourceBundle) {

        UserSession session = UserSession.viewInstance();
        System.out.println(UserSession.getInstance(username, UserSession.user_id));
        sidebar_loggedInAs.setText("Logged In As");
        sidebar_preferredAlias.setText(username);

        sidebar_logout_btn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                shiftScene(event, "login.fxml", "Hello", null);
                session.cleanUserSession();
            }
        });
    }
}
