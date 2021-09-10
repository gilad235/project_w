package com.example.tindog;

import android.app.Application;

public class CurrentUserDetails extends Application {
    private String userId;
    public String getUserID(){
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public void onCreate() {

        super.onCreate();
        instance=this;
        this.userId="";
    }

    private static CurrentUserDetails instance=null;
    public static CurrentUserDetails getInstance(){return instance;}

}
