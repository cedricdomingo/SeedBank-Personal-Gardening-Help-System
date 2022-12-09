//Filename: UserSession.java
//Author(s): Allen Cedric Domingo


package com.example.demo;

//UserSession class - Code written by @Allen Cedric Domingo
public final class UserSession {
    private static UserSession instance;
    public static String username = null;
    public static int user_id = 0;

    public UserSession(String username, int user_id) {
        this.username = username;
        this.user_id = user_id;
    }

    public String getUserName() {
        return username;
    }
    public static int getUserID() {
        return user_id;
    }

    //getInstance function - Code written by @Allen Cedric Domingo
    public static UserSession getInstance(String username, int user_id) {
        if(instance.user_id==0) {
            instance = new UserSession(username, user_id);
        }
        return instance;
    }

    //viewInstance function - Code written by @Allen Cedric Domingo
    public static UserSession viewInstance(){
        return instance;
    }

    //cleanUserSession function - Code written by @Allen Cedric Domingo
    public static void cleanUserSession() {
        username = "";
        user_id = 0;
    }

    //@Override toString function - Code written by @Allen Cedric Domingo
    @Override
    public String toString() {
        return "UserSession{" +
                "username='" + username + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}