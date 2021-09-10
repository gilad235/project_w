package com.example.tindog.data;


import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@IgnoreExtraProperties
public class User {

    public String id;
    public String name;
    public String pic;
    public String phone;
    public String dogName;
//    public ArrayList<User> friends;

    public User() {
//        this.friends=new ArrayList<>();
//        this.friends=new HashMap<>();

        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public User(String id,String name, String pic, String phone, String dogname) {
        this.id=id;
        this.name = name;
        this.pic = pic;
        this.phone = phone;
        this.dogName = dogname;
//        this.friends=new ArrayList<>();
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }
//
//    public ArrayList<User> getFriends() {
//        return friends;
//    }
//
//    public void setFriends(ArrayList<User> friends) {
//        this.friends = friends;
//    }

//    public List<String> getFriends() {
//        return friends;
//    }
//
//    public void setFriends(List<String> friends) {
//        this.friends = friends;
//    }
//
//    public void addFriend(User friend){
//        this.friends.add(friend.getId());
//    }
}