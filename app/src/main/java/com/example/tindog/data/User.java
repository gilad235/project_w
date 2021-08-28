package com.example.tindog.data;


import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class User {


    public String name;
    public String pic;
    public String phone;
    public String dogName;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public User(String name, String pic, String phone, String dogname) {
        this.name = name;
        this.pic = pic;
        this.phone = phone;
        this.dogName = dogname;
    }

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("pic", pic);
        result.put("phone", phone);
        result.put("dogName", dogName);
        return result;
    }
    // [END post_to_map]
}