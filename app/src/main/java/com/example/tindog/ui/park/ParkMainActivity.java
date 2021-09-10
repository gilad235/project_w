package com.example.tindog.ui.park;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.tindog.R;
import com.example.tindog.databinding.ActivityDogDetailsBinding;
import com.example.tindog.databinding.ActivityDogFormBinding;

public class ParkMainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.park_framelayout);
        ShowParkChekinFragment fragment = new ShowParkChekinFragment();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.commit();





    }



}
