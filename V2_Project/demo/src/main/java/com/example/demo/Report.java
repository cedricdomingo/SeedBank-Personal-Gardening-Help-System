//Filename: Report.java
//Author(s): Allen Cedric Domingo

package com.example.demo;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import java.util.Date;

//Report class - Code written by @Allen Cedric Domingo
public class Report {
    private int report_id;
    private int user_id;
    private int report_type;
    private Date report_date;

    public Report(int report_id, int user_id, int report_type, Date report_date) {
        this.report_id = report_id;
        this.user_id = UserSession.getUserID();
        this.report_type = report_type;
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        this.report_date = sqlDate;
    }

    public int getReport_id(){
        return report_id;
    }
    public int getUser_id(){
        return user_id;
    }
    public int getReport_type(){
        return report_type;
    }
    public Date getReport_date(){
        return report_date;
    }



}