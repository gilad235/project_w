package com.example.tindog.data;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Park {
    public String name;
    public float x;
    public float y;


    public Park() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Park(String name, float x, float y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("x", x);
        result.put("y", y);
        return result;
    }
}
