package com.example.tindog;

import android.app.Application;

import com.example.tindog.data.User;
import com.example.tindog.ui.tinder.FilterData;
import com.google.firebase.database.FirebaseDatabase;

public class CurrentUserDetails extends Application {
    private String userId;

    public FilterData userFilter;
    private User curUser;
    public String getUserID(){
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public User getCurUser() {
        return curUser;
    }

    public void setCurUser(User curUser) {
        this.curUser = curUser;
    }

    @Override
    public void onCreate() {


        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        instance=this;
        this.userId="";
        this.curUser=null;
        this.userFilter = new FilterData();
    }

    private static CurrentUserDetails instance=null;
    public static CurrentUserDetails getInstance(){return instance;}

}