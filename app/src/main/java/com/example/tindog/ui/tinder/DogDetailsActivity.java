package com.example.tindog.ui.tinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.tindog.R;
import com.example.tindog.data.Dog;
import com.example.tindog.databinding.ActivityDogDetailsBinding;

public class DogDetailsActivity extends AppCompatActivity {

    private ActivityDogDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_details);

        binding = ActivityDogDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



// To retrieve object in second Activity
        Dog dog = (Dog )getIntent().getSerializableExtra("dog");

        binding.dogDetailsName.setText(dog.name);
        if (dog.male){
            binding.dogGender.setText("Gender: male");
        } else {
            binding.dogGender.setText("Gender: female");
        }

        binding.dogAge.setText("Age: " + dog.age);
        binding.dogPhone.setText("Phone: " + dog.phone);

        binding.dogText.setText("Description: " + dog.text);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        this.finish();
        return true;
    }
}