package com.huji.tindog.ui.tinder;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

public class openFragmentTindog extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, new DogFragment()).commit();}
    }




}

