//Filename: ReportsController.java
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
import java.util.ResourceBundle;

//ReportsController class - Code written by @Allen Cedric Domingo
public class ReportsController implements Initializable {

    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    @FXML
    private Label sidebar_loggedInAs;
    @FXML
    private Label sidebar_preferredAlias;
    @FXML
    private Button sidebar_logout_btn;
    @FXML
    private TableView<Report> reports_reports_table;
    @FXML
    private TableColumn<Report, Integer> report_id_column;
    @FXML
    private TableColumn<Report, Integer> user_id_column;
    @FXML
    private TableColumn<Report, Integer> report_type_column;
    @FXML
    private TableColumn<Report, Date> report_date_column;
    private int report_id;
    private int user_id;
    private int report_type;
    private Date report_date;

    @FXML
    private ComboBox<String> reports_type_comboBox;
    ObservableList<Report> data = FXCollections.observableArrayList();


    //initialize method to set-up tableview - Code written by @Allen Cedric Domingo
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");
            String query = "select * from report where user_id = " + UserSession.user_id;
            statement = connection.prepareStatement(query);
            set = statement.executeQuery();

            while (set.next()) {
                data.add(new Report(
                        set.getInt("report_id"),
                        set.getInt("user_id"),
                        set.getInt("report_type"),
                        set.getDate("report_date")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
            ;
            System.out.println("error");
        }

        report_id_column.setCellValueFactory(new PropertyValueFactory<>("report_id"));
        user_id_column.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        report_type_column.setCellValueFactory(new PropertyValueFactory<>("report_type"));
        report_date_column.setCellValueFactory(new PropertyValueFactory<>("report_date"));
        reports_reports_table.setItems(data);
        System.out.println(data);


        UserSession session = UserSession.viewInstance();
        System.out.println(UserSession.getInstance(UserSession.username, UserSession.user_id));
        sidebar_loggedInAs.setText("Logged In As");
        sidebar_preferredAlias.setText(UserSession.username);

        report_id_column = new TableColumn<Report, Integer>("report_id");
        user_id_column = new TableColumn<Report, Integer>("user_id");
        report_type_column = new TableColumn<Report, Integer>("report_type");
        report_date_column = new TableColumn<Report, Date>("report_date");

        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Top 5 Purchased Seeds",
                        "Top 5 Harvested Seeds",
                        "Top 5 Wasted Seeds",
                        "Next Year's Gardening Plan"

                );
        final ComboBox comboBox = new ComboBox(options);

        sidebar_logout_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Driver.shiftScene(event, "login.fxml", "Hello", null);
                session.cleanUserSession();
            }
        });
    }


    /**
     * Initializes the controller class.
     */
    Connection connection;
    PreparedStatement statement;
    ResultSet set = null;

    //readEntry method - Code written by @Allen Cedric Domingo
    public void readEntry(ActionEvent event){

        int reportType = 0;
        String option1 = "Top 5 Harvested Seeds";
        String option2 = "Top 5 Purchased Seeds";
        String option3 = "Top 5 Wasted Seeds";
        String option4 = "Next Year's Gardening Plan";
        String selected_type = (String)reports_type_comboBox.getValue();
        if (selected_type.equals(option1)) {
            reportType = 1;
        } else if (selected_type.equals(option2)) {
            reportType = 2;
        }else if (selected_type.equals(option3)) {
            reportType = 3;
        }else if (selected_type.equals(option4)) {
            reportType = 4;
        }
        Report report = new Report(0, this.user_id, reportType, this.report_date);
        addReportToDatabase(report);
    }

    /**
     *  Takes a report and sends the information to the Report database
     */
    //readEntry method - Code written by @Allen Cedric Domingo
    public void addReportToDatabase (Report report){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");
            Statement statement = connection.createStatement();
            String query = new String("INSERT INTO `test`.`report` (`report_id`,`user_id`, "
                    + "`report_type`,`report_date`)"  +
                    "VALUES (" + report.getReport_id()  + ","+ report.getUser_id() + "," +
                    report.getReport_type() + "," +  "DATE '"+ report.getReport_date() + "'" + ");");
            statement.executeUpdate(query);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

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
}