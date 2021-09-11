package com.example.tindog;

import android.app.Application;

import com.example.tindog.ui.tinder.FilterData;

public class CurrentUserDetails extends Application {
    private String userId;
    public FilterData userFilter;
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
        this.userFilter = new FilterData();
    }

    private static CurrentUserDetails instance=null;
    public static CurrentUserDetails getInstance(){return instance;}

}
