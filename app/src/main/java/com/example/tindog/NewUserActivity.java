package com.example.tindog;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tindog.data.User;
import com.example.tindog.ui.fff.openFragmentFFF;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewUserActivity extends AppCompatActivity {
    String str_dog_name = "";
    String str_user_name = "";
    String str_dog_phone = "";
    String str_dog_pic = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_user_login);

        EditText dog_name = findViewById(R.id.dog_name);
        EditText user_name = findViewById(R.id.user_name);
        EditText dog_phone = findViewById(R.id.dog_phone);
        EditText dog_pic = findViewById(R.id.dog_pic);
        Button add_new_user_button = findViewById(R.id.add_new_user);


        dog_name.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                str_dog_name = (dog_name.getText().toString());
                setSaveButtonStatus(add_new_user_button);
            }
        });

        user_name.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                str_user_name = (user_name.getText().toString());
                setSaveButtonStatus(add_new_user_button);
            }
        });

        dog_phone.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                str_dog_phone = (dog_phone.getText().toString());
                setSaveButtonStatus(add_new_user_button);

            }
        });
        dog_pic.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                str_dog_pic = (dog_pic.getText().toString());
            }
        });


        add_new_user_button.setOnClickListener(v -> {
            User cur_user=new User(CurrentUserDetails.getInstance().getUserID(),str_user_name,str_dog_pic,str_dog_phone,str_dog_name);
            DatabaseReference database = FirebaseDatabase.getInstance().getReference();
            DatabaseReference myRef = database.child("users");
            myRef.child(cur_user.getId()).setValue(cur_user);
            CurrentUserDetails userSingleton = CurrentUserDetails.getInstance();
            userSingleton.setCurUser(cur_user);
            Intent i = new Intent(v.getContext(), MainActivity.class);
            finish();
            v.getContext().startActivity(i);
        });


    }

    private void setSaveButtonStatus(Button save) {
        save.setEnabled(!str_dog_name.equals("") && !str_user_name.equals("") && !str_dog_phone.equals(""));
    }


}
