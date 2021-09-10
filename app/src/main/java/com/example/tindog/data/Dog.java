package com.example.tindog.data;


import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Dog implements Serializable {

    public String name;
    public int age;
    public boolean male;
    public boolean castrated;
    public String pic;
    public String phone;
    public String text;

    public Dog() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Dog(String name, int age, boolean male, boolean castrated, String pic) {
        this.name = name;
        this.age = age;
        this.male = male;
        this.castrated = castrated;
        this.pic = pic;
        this.phone = phone;
        this.text = text;
    }

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("age", age);
        result.put("male", male);
        result.put("castrated", castrated);
        result.put("pic", pic);
        result.put("phone", phone);
        result.put("text", text);
        return result;
    }
    // [END post_to_map]
}