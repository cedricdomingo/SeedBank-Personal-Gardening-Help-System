//Filename: SeedEntry.java
//Author(s): Allen Cedric Domingo

package com.example.demo;
import java.util.Date;

//SearchEntry class - Code written by @Allen Cedric Domingo
public class SeedEntry {
    private int entry_id;
    private int user_id;
    private int seed_id;
    private Date entry_date;
    private Date expiry_date;
    private int quantity_in_grams;
    private String method_obtained;
    private String status;

    public SeedEntry (){
    }

    public SeedEntry(int entry_id, int user_id, int seed_id, Date entry_date, int quantity_in_grams, String method_obtained, Date expiry_date, String status) {
        this.entry_id = entry_id;
        this.user_id = UserSession.getUserID();
        this.seed_id = seed_id;
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        this.entry_date = sqlDate;
        this.quantity_in_grams = quantity_in_grams;
        this.method_obtained = method_obtained;
        this.expiry_date = expiry_date;
        this.status = status;
    }

    public int getEntry_id(){
        return entry_id;
    }
    public int getUser_id(){
        return user_id;
    }
    public int getSeed_id(){
        return seed_id;
    }
    public Date getEntry_date(){
        return entry_date;
    }
    public int getQuantity_in_grams(){
        return quantity_in_grams;
    }
    public String getMethod_obtained(){
        return method_obtained;
    }
    public Date getExpiry_date(){
        return expiry_date;
    }
    public String getStatus(){
        return status;
    }


}
