package com.huji.tindog.ui.park;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.huji.tindog.data.User;
import com.google.firebase.database.Exclude;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class checkInWarrper {
    public String timeCreated;
    public User user;


    @RequiresApi(api = Build.VERSION_CODES.O)
    checkInWarrper(User usr, String cur){
        user=usr;
        timeCreated =cur;

    }

    checkInWarrper(){

    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", user.toString());
        result.put("time", timeCreated);
        return result;
    }

    @NonNull
    @Override
    public String toString() {
        return "checkInWarrper{" +
                "time=" + timeCreated +
                ", user=" + user +
                '}';
    }



    @RequiresApi(api = Build.VERSION_CODES.O)

    public  LocalDateTime turnStirngToDate(String s){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        return LocalDateTime.parse(s, formatter); }


    @Override
    public boolean equals(Object o){
        try{
            checkInWarrper t = (checkInWarrper) o;
            return this.user.id.equals(t.user.id);
        }
        catch (Exception e){
            return false;
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.O)


    public long getEditDate() {
        LocalDateTime fromDateTime = turnStirngToDate(timeCreated);
        LocalDateTime toDateTime =java.time.LocalDateTime.now() ;

        LocalDateTime tempDateTime = LocalDateTime.from( fromDateTime );

        long years = tempDateTime.until( toDateTime, ChronoUnit.YEARS );
        tempDateTime = tempDateTime.plusYears( years );

        long months = tempDateTime.until( toDateTime, ChronoUnit.MONTHS );
        tempDateTime = tempDateTime.plusMonths( months );

        long days = tempDateTime.until( toDateTime, ChronoUnit.DAYS );
        tempDateTime = tempDateTime.plusDays( days );

        long hours = tempDateTime.until( toDateTime, ChronoUnit.HOURS );
        tempDateTime = tempDateTime.plusHours( hours );
        long minutes = tempDateTime.until( toDateTime, ChronoUnit.MINUTES );
        tempDateTime = tempDateTime.plusMinutes( minutes );


        if(years<1 && months<1 && days<1 && hours<1){
            return minutes;
        }
       return 1000;


    }

}

