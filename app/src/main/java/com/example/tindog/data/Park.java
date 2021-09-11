package com.example.tindog.data;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Park {
    public String name;
    public float x;
    public float y;
    public ArrayList<User> checkins;


    public Park() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Park(String name, float x, float y,ArrayList<User> users) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.checkins = users;
    }


    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("x", x);
        result.put("y", y);
        result.put("users", checkins);

        return result;
    }

    public void add_checkIn(User user){
        checkins.add(user);
        //add the checking to firebase here


    }
}
