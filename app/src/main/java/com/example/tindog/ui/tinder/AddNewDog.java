package com.example.tindog.ui.tinder;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.tindog.R;
import com.example.tindog.data.Dog;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNewDog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_dog_tindog);

        EditText dog_name = findViewById(R.id.dog_name);
        RadioGroup radioGroupGender = (RadioGroup) findViewById(R.id.choose_gender);
        radioGroupGender.clearCheck();
        RadioButton male = findViewById(R.id.male);
        RadioButton female = findViewById(R.id.female);
        EditText age = findViewById(R.id.age);
        EditText dog_phone = findViewById(R.id.dog_phone);
        EditText dog_pic = findViewById(R.id.dog_pic);
        EditText dog_text = findViewById(R.id.dog_text);
        Button save = findViewById(R.id.save);
        Dog curDog = new Dog();
        female.setOnClickListener(v -> {
            curDog.setMale(false);
            setSaveButtonStatus(save, curDog);
        });
        male.setOnClickListener(v -> {
            curDog.setMale(true);
            setSaveButtonStatus(save, curDog);
        });

        dog_name.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                curDog.setName(dog_name.getText().toString());
                setSaveButtonStatus(save, curDog);
            }
        });
        age.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                try {
                    curDog.setAge(Double.parseDouble(age.getText().toString()));
                    setSaveButtonStatus(save, curDog);
                }
                catch (Exception e){
                    age.setText(age.getText());
                }
                }
        });
        dog_phone.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                curDog.setPhone(dog_phone.getText().toString());
                setSaveButtonStatus(save, curDog);

            }
        });
        dog_pic.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                curDog.setPic(dog_pic.getText().toString());
                setSaveButtonStatus(save, curDog);

            }
        });

        dog_text.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                curDog.setText(dog_text.getText().toString());
                setSaveButtonStatus(save, curDog);
            }
        });

    save.setOnClickListener(v->{
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference myRef = database.child("dogs");
        myRef.child(curDog.getName()).setValue(curDog);
        Intent i = new Intent(v.getContext(),openFragment.class);
        finish();
        v.getContext().startActivity(i);
    });


    }

    private void setSaveButtonStatus(Button save, Dog curDog) {
        save.setEnabled(curDog.allFill());
    }


}
