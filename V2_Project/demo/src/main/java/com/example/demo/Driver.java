//Filename: Driver.java
//Author(s): Allen Cedric Domingo
/*
Code Reference: https://www.youtube.com/watch?v=ltX5AtW9v30&t=59s
*/

package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;;

import java.io.IOException;
import java.sql.*;


//Driver class - Code written by @Allen Cedric Domingo
public class Driver {

    //shiftScene Method - Code written by @Allen Cedric Domingo
    public static void shiftScene(ActionEvent event, String fxmlFile, String title, String username) {
        Parent root = null;

        if (username != null) {
            try {
                FXMLLoader loader = new FXMLLoader(Driver.class.getResource(fxmlFile));
                root = loader.load();
                DashboardController dashboardController = loader.getController();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(Driver.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 1366, 768));
        stage.show();
    }

    public static void shiftScene1(ActionEvent event, String fxmlFile, String title, String username) {
        Parent root = null;

        if (username != null) {
            try {
                FXMLLoader loader = new FXMLLoader(Driver.class.getResource(fxmlFile));
                root = loader.load();
                SeedEntryController seedEntryController = loader.getController();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(Driver.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 1366, 768));
        stage.show();
    }

    //userSignUp Method - Code written by @Allen Cedric Domingo
    public static void userSignUp(ActionEvent event, String username, String password) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM RegisteredUser WHERE username = ?");
            psCheckUserExists.setString(1, username);
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("User already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username.");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO RegisteredUser(username, password) VALUES (?, ?)");
                psInsert.setString(1, username);
                psInsert.setString(2, password);
                psInsert.executeUpdate();

                shiftScene(event, "dashboard.fxml", "Hello", username);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        }
    }

    //userLogIn Method - Code written by @Allen Cedric Domingo
    public static void userLogIn(ActionEvent event, String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://Localhost:3306/demo", "root", "");
            preparedStatement = connection.prepareStatement("SELECT password, user_id FROM RegisteredUser WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();


            if (!resultSet.isBeforeFirst()) {
                System.out.println("Not able to locate User in Database!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Username or Password are Incorrect!");
                alert.show();
            } else {
                while (resultSet.next()) {
                    int user_id = resultSet.getInt("user_id");
                    String retrievedPassword = resultSet.getString("password");
                    if (retrievedPassword.equals(password)) {
                        UserSession.getInstance(username, user_id);
                        System.out.println(UserSession.getInstance(username, user_id));
                        shiftScene(event, "dashboard.fxml", "Hello", username);
                    } else {
                        System.out.println("Incorrect password!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("The Username or Password are Incorrect!");
                        alert.show();
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //goToDashboard Method - Code written by @Allen Cedric Domingo
    public static void goToDashboard(ActionEvent event, String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://Localhost:3306/demo", "root", "");
            preparedStatement = connection.prepareStatement("SELECT password FROM RegisteredUser WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Not able to locate User in Database!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Username or Password are Incrorrect!");
                alert.show();
            } else {
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    if (retrievedPassword.equals(password)) {
                        shiftScene(event, "dashboard.fxml", "Hello", username);
                    } else {
                        System.out.println("Incorrect password!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("The Username or Password are Incrorrect!");
                        alert.show();
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
