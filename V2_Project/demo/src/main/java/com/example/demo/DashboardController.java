//Filename: DashboardController.java
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

//DashboardController class - Code written by @Allen Cedric Domingo and @Allen Cedric Domingo
public class DashboardController implements Initializable {

    @FXML
    private Button sidebar_logout_btn;
    @FXML
    private Button sidebar_dashboard_btn;
    @FXML
    private Label sidebar_loggedInAs;
    @FXML
    private Label sidebar_preferredAlias;
    @FXML
    private Label dashboard_preferredAlias;
    @FXML
    private Label welcomeText;
    private Stage stage;
    private Scene scene;
    private Parent root;

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
    public void goToReports(ActionEvent event)  throws IOException {
        root = FXMLLoader.load(getClass().getResource("reports.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void logoutButton(ActionEvent event) throws IOException {
        UserSession.cleanUserSession();
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //DASHBOARD SEED INVENTORY TABLEVIEW - Code written by @Allen Cedric Domingo
    @FXML
    private TableView<SeedEntry> seedInventory_table;
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection connection;
        PreparedStatement statement;
        ResultSet set = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");
            String query = "select * from seedentry WHERE user_id = " + UserSession.user_id;
            statement = connection.prepareStatement(query);
            set = statement.executeQuery();

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

        seedInventory_table.setItems(data);
        System.out.println(data);

        UserSession session = UserSession.viewInstance();
        System.out.println(UserSession.getInstance(UserSession.username, UserSession.user_id));
        sidebar_loggedInAs.setText("Logged In As");
        sidebar_preferredAlias.setText(UserSession.username);
        dashboard_preferredAlias.setText(UserSession.username);

        entry_id_column = new TableColumn<SeedEntry, Integer>("entry_id");
        user_id_column = new TableColumn<SeedEntry, Integer>("user_id");
        seed_id_column = new TableColumn<SeedEntry, Integer>("seed_id");
        entry_date_column = new TableColumn<SeedEntry, Date>("entry_date");
        quantity_in_grams_column = new TableColumn<SeedEntry, Integer>("quantity_in_grams");
        method_obtained_column = new TableColumn<SeedEntry, String>("method_obtained");
        expiry_date_column = new TableColumn<SeedEntry, Date>("expiry_date");
        status_column = new TableColumn<SeedEntry, String>("status");
        sidebar_logout_btn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Driver.shiftScene(event, "login.fxml", "Hello", null);
                session.cleanUserSession();
            }
        });
    }

    //MARK AS WASTED BUTTON FUNCTIONS - Code written by @Allen Cedric Domingo
    public void markAsPlanted(){
        SeedEntry seedEntry = seedInventory_table.getSelectionModel().getSelectedItem();

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

    //MARK AS WASTED BUTTON FUNCTION - Code written by @Allen Cedric Domingo
    public void markAsWasted(){
        SeedEntry seedEntry = seedInventory_table.getSelectionModel().getSelectedItem();

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

    //viewAlerts function - Code written by @Allen Cedric Domingo
    public void viewAlerts (ActionEvent event){
        Alert expiredAlertMSG = new Alert(Alert.AlertType.WARNING);
        Alert lowStockAlertMSG = new Alert(Alert.AlertType.WARNING);
        Alert noAlert = new Alert(Alert.AlertType.CONFIRMATION);

        boolean hasExpiredAlert = false;
        boolean hasLowStockAlert = false;
        Connection connection;
        String expiredAlert = "";
        String lowStockAlert = "";

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");
            String lowStock = "SELECT entry_id, seedtype.name, quantity_in_grams, expiry_date from seedentry, seedtype WHERE quantity_in_grams < 50 " +
                    "AND user_id = "+ UserSession.getUserID() +  " AND status = 'READY' AND seedentry.seed_id = seedtype.seed_id;";
            String expiredQuery = "SELECT entry_id, seedtype.name, quantity_in_grams, expiry_date from seedentry, seedtype WHERE expiry_date < date_add(curdate(), INTERVAL 1 YEAR) " +
                    "AND user_id = "+ UserSession.getUserID() +  " AND status = 'READY' AND seedentry.seed_id = seedtype.seed_id;";
            Statement statement = connection.createStatement();
            ResultSet lowStockSet = statement.executeQuery(lowStock);

            while (lowStockSet.next()){
                hasLowStockAlert = true;
                lowStockAlert += (lowStockSet.getString(1) + ", "
                        +lowStockSet.getString(2) + ", "
                        +lowStockSet.getString(3) + ", "
                        +lowStockSet.getString(4) + "\n");
            }

            ResultSet expSet = statement.executeQuery(expiredQuery);

            while (expSet.next()){
                hasExpiredAlert = true;
                expiredAlert += (expSet.getString(1) + ", "
                        +expSet.getString(2) + ", "
                        +expSet.getString(3) + ", "
                        +expSet.getString(4) + "\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }

        if (hasLowStockAlert == true) {
            lowStockAlertMSG.setContentText("Inventory Check: The following \"Ready\" seeds are low in stock, please restock: \n" +
                    "\n" +
                    "Entry, Name, Quantity, Exp Date \n" + lowStockAlert);
            lowStockAlertMSG.show();
        }

        if (hasExpiredAlert == true) {
                expiredAlertMSG.setContentText("Inventory Check: The following \"Ready\" seeds have an expiry of less than one year, please review in the table below: \n" +
                        "\n" +
                        "Entry, Name, Quantity, Exp Date \n" + expiredAlert);
                expiredAlertMSG.show();
        }

        if (hasExpiredAlert == false && hasLowStockAlert == false){
            noAlert.setContentText("Checking Inventory: No inventory issues!");
            noAlert.show();
        }
    }
}

