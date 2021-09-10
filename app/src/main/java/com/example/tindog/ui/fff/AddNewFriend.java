package com.example.tindog.ui.fff;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.tindog.CurrentUserDetails;
import com.example.tindog.R;
import com.example.tindog.data.Dog;
import com.example.tindog.data.User;
import com.example.tindog.ui.tinder.openFragmentTindog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddNewFriend extends AppCompatActivity {
    String str_dog_name = "";
    String str_user_name = "";
    String str_dog_phone = "";
    User friend_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_dog_fff);

        EditText dog_name = findViewById(R.id.dog_name);
        EditText user_name = findViewById(R.id.user_name);
        EditText dog_phone = findViewById(R.id.dog_phone);
        Button add_friend_button = findViewById(R.id.add_friend_fff);


        dog_name.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                str_dog_name = (dog_name.getText().toString());
                setSaveButtonStatus(add_friend_button);
            }
        });

        user_name.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                str_user_name = (user_name.getText().toString());
                setSaveButtonStatus(add_friend_button);
            }
        });

        dog_phone.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                str_dog_phone = (dog_phone.getText().toString());
                setSaveButtonStatus(add_friend_button);

            }
        });


        add_friend_button.setOnClickListener(v -> {
            DatabaseReference database = FirebaseDatabase.getInstance().getReference();
            DatabaseReference myRef = database.child("users");
            myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    } else {
                        Log.d("firebase", String.valueOf(task.getResult().getValue()));
                        boolean user_exist_flag = false;
                        for (DataSnapshot snapshot : task.getResult().getChildren()) {
                            User user = snapshot.getValue(User.class);
                            if (user.getName().equals(str_user_name) && user.getDogName().equals(str_dog_name) && user.getPhone().equals(str_dog_phone)) {
                                friend_user = user;
                                user_exist_flag = true;
                                break;
                            }
                        }
                        if (user_exist_flag) {
                            CurrentUserDetails userSingleton = CurrentUserDetails.getInstance();

                            DatabaseReference myRef_1 = myRef.child(userSingleton.getUserID());
                            DatabaseReference myRef_2 = myRef_1.child("friends");
//                            Map<String, User> f = new HashMap<>();
//                            f.put(friend_user.getId(), friend_user);

                            myRef_2.push().setValue(friend_user);
//
//myRef_2.setValue(friend_user,friend_user.getId());

                            Intent i = new Intent(v.getContext(), openFragmentFFF.class);
                            finish();
                            v.getContext().startActivity(i);
                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(), "user not found", Toast.LENGTH_SHORT);
                            toast.show();
//                                                        //TODO: what happen when user doesn't exist.
//
                        }
                    }
                }
            });

        });


    }

    private void setSaveButtonStatus(Button save) {
        save.setEnabled(!str_dog_name.equals("") && !str_user_name.equals("") && !str_dog_phone.equals(""));
    }


}