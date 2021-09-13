package com.huji.tindog.data;


import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Dog implements Serializable {

    public String name;
    public double age;
    public boolean male;
    public boolean isGenderFill;
    public boolean castrated;
    public String pic;
    public String phone;
    public String text;

    public Dog() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
        this.age = -1.0;
        this.isGenderFill = false;


    }

    public Dog(String name, double age, boolean male, boolean castrated, String pic) {
        this.name = name;
        this.age = age;
        this.male = male;
        this.isGenderFill = true;
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


    public void setAge(double age) {
        this.age = age;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCastrated(boolean castrated) {
        this.castrated = castrated;
    }

    public void setMale(boolean male) {
        this.male = male;
        this.isGenderFill = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public boolean allFill() {
        return ((this.name != null && !this.name.equals("")) && this.age != -1 && (this.phone != null && !this.phone.equals("")) && (this.text != null && !this.text.equals("")) && this.isGenderFill);
    }
}