package com.huji.tindog.ui.dogform;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import com.huji.tindog.databinding.ActivityDogFormBinding;
import com.huji.tindog.databinding.ActivityMainBinding;

public class DogFormActivity extends AppCompatActivity {

    private ActivityDogFormBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDogFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        this.finish();
        return true;
    }
}