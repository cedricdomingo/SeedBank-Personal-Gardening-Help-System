//Filename: SearchController.java
//Author(s): Allen Cedric Domingo

package com.example.demo;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.collections.FXCollections;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.ResourceBundle;

//SearchEntry class - Code written by @Allen Cedric Domingo and @Allen Cedric Domingo
public class SeedEntryController implements Initializable {

    // If you ComboBox is going to display Strings, you should define that datatype here
    @FXML
    private ComboBox<String> seedEntry_type_comboBox;
    @FXML
    private ComboBox<String> seedEntry_methodObtained_comboBox;
    @FXML
    private Button sidebar_logout_btn;
    @FXML
    private Label sidebar_loggedInAs;
    @FXML
    private Label sidebar_preferredAlias;
    @FXML
    private Label seedEntry_newEntrySaved;
    @FXML
    private TableView<SeedEntry> seedEntry_table;
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

    @FXML
    private ImageView seedEntry_placeholder_img;



    ObservableList<SeedEntry> data = FXCollections.observableArrayList();

    //initialize method - Code written by @Allen Cedric Domingo
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection connection;
        PreparedStatement statement;
        ResultSet set = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");
            String query = "select * from seedentry where user_id = " + UserSession.user_id;
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

        seedEntry_table.setItems(data);
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

        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Harvested",
                        "Purchased"
                );
        final ComboBox comboBox = new ComboBox(options);

        seedEntry_type_comboBox.setItems(FXCollections.observableArrayList(getData()));
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

    @FXML
    private Label welcomeText;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField seedEntry_quantity_textField,
            seedEntry_day_textField,
            seedEntry_month_textfield,
            seedEntry_year_textfield;

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

//markAsPlanted function - Code written by @Allen Cedric Domingo
public void markAsPlanted(){
    SeedEntry seedEntry = seedEntry_table.getSelectionModel().getSelectedItem();

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
    //markAsWasted function - Code written by @Allen Cedric Domingo
public void markAsWasted(){
    SeedEntry seedEntry = seedEntry_table.getSelectionModel().getSelectedItem();

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
    //getData function - Code written by @Allen Cedric Domingo
    private List<String> getData() {

        // Define the data you will be returning, in this case, a List of Strings for the ComboBox
        List<String> options = new ArrayList<>();
        Connection connection;
        PreparedStatement statement;
        ResultSet set = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");
            String query = "select name from seedtype";
            statement = connection.prepareStatement(query);
            set = statement.executeQuery();

            while (set.next()) {
                options.add(set.getString("name"));
            }

            statement.close();
            set.close();

            // Return the List
            return options;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (set != null) {
                try {
                    set.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }

            }
        }
    }

    //updateImage method - Code written by: @Allen Cedric Domingo
    //Image set also compiled by @Allen Cedric Domingo
    //Full album of photos used in SQL database can be found at: https://imgur.com/a/3ffrTVt
    @FXML
    public void updateImage(ActionEvent event) {
        String seedNameAdd = seedEntry_type_comboBox.getValue();


        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");
            Statement statement = connection.createStatement();
            String query = new String("SELECT image_hyperlink FROM seedtype WHERE name = " + "'" + seedNameAdd +"'");
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String imageHyperlink = resultSet.getString("image_hyperlink");
                String imageSource = imageHyperlink;
                Image image = new Image(imageSource);
                seedEntry_placeholder_img.setImage(image);
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     *Reads the information entered in the New Entry page Twhen Enter is pressed
     */
    //readEntry function - Code written by @Allen Cedric Domingo
    public void readEntry(ActionEvent event){
        String seedNameAdd = seedEntry_type_comboBox.getValue();
        int seedID = 0;

        //Finds the seedID number from the seed name
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");
            Statement statement = connection.createStatement();
            String query = new String("SELECT seed_id FROM seedtype WHERE name = " + "'" + seedNameAdd +"'");
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                seedID = resultSet.getInt("seed_id");
            }

        } catch (Exception e){
            e.printStackTrace();
        }


        int newQty = Integer.parseInt(seedEntry_quantity_textField.getText());
        String addMethod = seedEntry_methodObtained_comboBox.getValue();
        System.out.println((String)seedEntry_methodObtained_comboBox.getValue());
        String strExpDate = (seedEntry_year_textfield.getText() + "-" + seedEntry_month_textfield.getText() + "-" + seedEntry_day_textField.getText());
        Date sqlExpDate = Date.valueOf(strExpDate);
        int user_id = UserSession.getUserID();
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        int quantity_in_grams = 0;
        String status = "Ready";
        SeedEntry newEntry = new SeedEntry(0, user_id, seedID, sqlDate, newQty, addMethod, sqlExpDate, status);

        addEntryToDatabase(newEntry);


    }

    /**
     *  Takes a seed entry and sends the information to the seedentry database
     */
    //getData function - Code written by @Allen Cedric Domingo
    public void addEntryToDatabase (SeedEntry s){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");
            Statement statement = connection.createStatement();
            String query = new String("INSERT INTO `test`.`seedentry` (`entry_id`, `user_id`,`entry_date`, "
                    + "`seed_id`,`quantity_in_grams`,`method_obtained`,`expiry_date`,`status`)" +
                    "VALUES (" + s.getEntry_id() + "," + s.getUser_id() + "," + "DATE '"+ s.getEntry_date() + "'" + ","+ s.getSeed_id() + "," +
                    s.getQuantity_in_grams() + "," + "\"" + s.getMethod_obtained() + "\"" + ","  + "DATE '"+ s.getExpiry_date() +
                    "'" + "," + "\"" + s.getStatus() + "\");");
            statement.executeUpdate(query);
            seedEntry_newEntrySaved.setVisible(true);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}


