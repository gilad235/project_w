package com.example.tindog.ui.park;

import androidx.annotation.NonNull;

import com.example.tindog.data.User;
import com.google.firebase.database.Exclude;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class checkInWarrper {
    public Date time;
    public User user;


    checkInWarrper(User usr, Date cur){
        user=usr;
        time=cur;

    }
    checkInWarrper(){}

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", user.toString());
        result.put("time", time);
        return result;
    }

    @NonNull
    @Override
    public String toString() {
        return "checkInWarrper{" +
                "time=" + time +
                ", user=" + user +
                '}';
    }
}
