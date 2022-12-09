///Filename: PlantingScheduleController.java
//Author(s): Allen Cedric Domingo

package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

//PlantingScheduleController class - Code written by @Allen Cedric Domingo
public class PlantingScheduleController implements Initializable {
    @FXML
    private Label welcomeText;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label sidebar_loggedInAs;
    @FXML
    private Button sidebar_logout_btn;
    @FXML
    private Label sidebar_preferredAlias;
    @FXML
    private Label dashboard_preferredAlias;


    @FXML
    private TextField PlantingSchedule_textfield;
    @FXML
    private TableView plantingSchedule_table;
    @FXML
    private TableColumn<SeedEntry, Integer> entry_id_column;
    @FXML
    private TableColumn<SeedEntry, Integer> user_id_column;
    @FXML
    private TableColumn<SeedEntry, Integer> seed_id_column;
    @FXML
    private TableColumn<SeedEntry, Date> entry_date_column;
    @FXML
    private TableColumn<SeedEntry, Integer> quantity_in_grams_column;
    @FXML
    private TableColumn<SeedEntry, String> method_obtained_column;
    @FXML
    private TableColumn<SeedEntry, Date> expiry_date_column;
    @FXML
    private TableColumn<SeedEntry, String> status_column;

    ObservableList<SeedEntry> data = FXCollections.observableArrayList();

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


    public void searchItems(ActionEvent event){
        String searchVal = PlantingSchedule_textfield.getText();

        searchPlantingTime(searchVal);

    }


    //searchPlantingTime method - Code written by @Allen Cedric Domingo and @Allen Cedric Domingo

    public void searchPlantingTime(String searchString){
        plantingSchedule_table.getItems().clear();
        ObservableList categoryResults = FXCollections.observableArrayList();
        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");
            Statement statement = connection.createStatement();
            String query = new String("SELECT * FROM seedentry,seedtype WHERE seedtype.planting_time LIKE '%" + searchString + "%' AND status = 'READY' AND seedentry.seed_id = seedtype.seed_id AND user_id =" + UserSession.user_id);
            ResultSet set = statement.executeQuery(query);
            while (set.next()) {
                data.add(new SeedEntry(
                        set.getInt("entry_id"),
                        set.getInt("user_id"),
                        set.getInt("seed_id"),
                        set.getDate("entry_date"),
                        set.getInt("quantity_in_grams"),
                        set.getString("method_obtained"),
                        set.getDate("expiry_date"),
                        set.getString("status")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
            ;
            System.out.println("error");
        }

        entry_id_column.setCellValueFactory(new PropertyValueFactory<>("entry_id"));
        user_id_column.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        seed_id_column.setCellValueFactory(new PropertyValueFactory<>("seed_id"));
        entry_date_column.setCellValueFactory(new PropertyValueFactory<>("entry_date"));
        quantity_in_grams_column.setCellValueFactory(new PropertyValueFactory<>("quantity_in_grams"));
        method_obtained_column.setCellValueFactory(new PropertyValueFactory<>("method_obtained"));
        expiry_date_column.setCellValueFactory(new PropertyValueFactory<>("expiry_date"));
        status_column.setCellValueFactory(new PropertyValueFactory<>("status"));

        plantingSchedule_table.setItems(data);
        System.out.println(data);

        UserSession session = UserSession.viewInstance();
        System.out.println(UserSession.getInstance(UserSession.username, UserSession.user_id));
        sidebar_loggedInAs.setText("Logged In As");
        sidebar_preferredAlias.setText(UserSession.username);

        entry_id_column = new TableColumn<SeedEntry, Integer>("entry_id");
        user_id_column = new TableColumn<SeedEntry, Integer>("user_id");
        seed_id_column = new TableColumn<SeedEntry, Integer>("seed_id");
        entry_date_column = new TableColumn<SeedEntry, Date>("entry_date");
        quantity_in_grams_column = new TableColumn<SeedEntry, Integer>("quantity_in_grams");
        method_obtained_column = new TableColumn<SeedEntry, String>("method_obtained");
        expiry_date_column = new TableColumn<SeedEntry, Date>("expiry_date");
        status_column = new TableColumn<SeedEntry, String>("status");

    }


//initialize method - Code written by @Allen Cedric Domingo

    public void initialize(URL location, ResourceBundle resources) {



        UserSession session = UserSession.viewInstance();
        System.out.println(UserSession.getInstance(UserSession.username, UserSession.user_id));
        sidebar_loggedInAs.setText("Logged In As");
        sidebar_preferredAlias.setText(UserSession.username);

        sidebar_logout_btn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Driver.shiftScene(event, "login.fxml", "Hello", null);
                session.cleanUserSession();
            }
        });
    }

    public void markAsPlanted(){
        SeedEntry seedEntry = (SeedEntry) plantingSchedule_table.getSelectionModel().getSelectedItem();

        int entryID = (seedEntry.getEntry_id());
        System.out.println(entryID);

        // Define the data you will be returning, in this case, a List of Strings for the ComboBox
        List<String> options = new ArrayList<>();
        Connection connection;
        PreparedStatement statement;
        ResultSet set = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");
            String query = "Update seedentry SET status = 'Planted' WHERE entry_id =" + entryID;
            statement = connection.prepareStatement(query);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            if (set != null) {
                try {
                    set.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void markAsWasted(){
        SeedEntry seedEntry = (SeedEntry) plantingSchedule_table.getSelectionModel().getSelectedItem();

        int entryID = (seedEntry.getEntry_id());
        System.out.println(entryID);

        // Define the data you will be returning, in this case, a List of Strings for the ComboBox
        List<String> options = new ArrayList<>();
        Connection connection;
        PreparedStatement statement;
        ResultSet set = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");
            String query = "Update seedentry SET status = 'Wasted' WHERE entry_id =" + entryID;
            statement = connection.prepareStatement(query);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            if (set != null) {
                try {
                    set.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }

    }

}