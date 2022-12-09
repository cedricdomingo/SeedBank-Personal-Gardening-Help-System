//Filename: SearchController.java
//Author(s): Allen Cedric Domingo, Allen Cedric Domingo

package com.example.demo;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.util.Callback;
import javafx.scene.*;
import javafx.application.*;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

//SearchController class - Code written by @Allen Cedric Domingo and @Allen Cedric Domingo
public class SearchController implements Initializable {
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
    private RadioButton search_category_radioButton, search_plantingTime_radioButton, search_expiryDate_radioButton, search_name_radio_button;
    @FXML
    private TextField search_searchBar_textfield;
    @FXML
    private TableView search_resultsTable;
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

    //selectCategory method - Code written by @Allen Cedric Domingo
    public void selectCategory(ActionEvent event){
        search_expiryDate_radioButton.setSelected(false);
        search_plantingTime_radioButton.setSelected(false);
        search_name_radio_button.setSelected(false);
    }
    //selectExpiry method - Code written by @Allen Cedric Domingo
    public void selectExpiry(ActionEvent event){
        search_category_radioButton.setSelected(false);
        search_plantingTime_radioButton.setSelected(false);
        search_name_radio_button.setSelected(false);

    }
    //selectPlanting method - Code written by @Allen Cedric Domingo
    public void selectPlanting(ActionEvent event){
        search_category_radioButton.setSelected(false);
        search_expiryDate_radioButton.setSelected(false);
        search_name_radio_button.setSelected(false);
    }

    //selectName method - Code written by @Allen Cedric Domingo
    public void selectName(ActionEvent event){
        search_category_radioButton.setSelected(false);
        search_expiryDate_radioButton.setSelected(false);
        search_plantingTime_radioButton.setSelected(false);
    }

    //selectItems method - Code written by @Allen Cedric Domingo
    public void searchItems(ActionEvent event){
        String searchVal = search_searchBar_textfield.getText();
        if(search_category_radioButton.isSelected()){
            searchCategory(searchVal);
        } else if(search_expiryDate_radioButton.isSelected()){
            searchExpiryDate(searchVal);
        } else if(search_plantingTime_radioButton.isSelected()){
            searchPlantingTime(searchVal);
        }
        else if(search_name_radio_button.isSelected()){
            searchName(searchVal);
        }
    }

    //searchCategory method - Code written by @Allen Cedric Domingo and @Allen Cedric Domingo
    public void searchCategory(String searchString){
        search_resultsTable.getItems().clear();
        ObservableList<SeedEntry> categoryResults = FXCollections.observableArrayList();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");
            Statement statement = connection.createStatement();

            String ql = ("SELECT * FROM seedentry,seedtype WHERE seedtype.category LIKE '%" + searchString +"%' AND seedentry.seed_id = seedtype.seed_id AND user_id =" + UserSession.user_id);
            ResultSet set = statement.executeQuery(ql);

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

        search_resultsTable.setItems(data);
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

    //searchPlantingTime method - Code written by @Allen Cedric Domingo and @Allen Cedric Domingo
    public void searchPlantingTime(String searchString){
        search_resultsTable.getItems().clear();
        ObservableList categoryResults = FXCollections.observableArrayList();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");
            Statement statement = connection.createStatement();
            String query = new String("SELECT * FROM seedentry,seedtype WHERE seedtype.planting_time LIKE '%" + searchString + "%' AND seedentry.seed_id = seedtype.seed_id AND user_id =" + UserSession.user_id);
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

        search_resultsTable.setItems(data);
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
    //searchExpiryDate method - Code written by @Allen Cedric Domingo and @Allen Cedric Domingo
    public void searchExpiryDate(String searchString){
        search_resultsTable.getItems().clear();

        ObservableList categoryResults = FXCollections.observableArrayList();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");
            Statement statement = connection.createStatement();
            String query = new String("SELECT * FROM seedentry,seedtype WHERE seedentry.expiry_date LIKE '%" + searchString + "%' AND seedentry.seed_id = seedtype.seed_id AND user_id =" + UserSession.user_id);
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

        search_resultsTable.setItems(data);
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

    //searchPlantingTime method - Code written by @Allen Cedric Domingo and @Allen Cedric Domingo
    public void searchName(String searchString){
        search_resultsTable.getItems().clear();
        ObservableList categoryResults = FXCollections.observableArrayList();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");
            Statement statement = connection.createStatement();
            String query = new String("SELECT * FROM seedentry,seedtype WHERE seedtype.name LIKE '%" + searchString + "%' AND seedentry.seed_id = seedtype.seed_id AND user_id =" + UserSession.user_id);
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

        search_resultsTable.setItems(data);
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ToggleGroup group = new ToggleGroup();
        search_category_radioButton.setToggleGroup(group);
        search_plantingTime_radioButton.setToggleGroup(group);
        search_expiryDate_radioButton.setToggleGroup(group);

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

    //markAsPlanted method - Code written by @Allen Cedric Domingo
    public void markAsPlanted(){
        SeedEntry seedEntry = (SeedEntry) search_resultsTable.getSelectionModel().getSelectedItem();

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

    //markAsWasted method - Code written by @Allen Cedric Domingo
    public void markAsWasted(){
        SeedEntry seedEntry = (SeedEntry) search_resultsTable.getSelectionModel().getSelectedItem();

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