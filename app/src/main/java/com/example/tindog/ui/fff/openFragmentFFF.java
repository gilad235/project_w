package com.example.tindog.ui.fff;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

public class openFragmentFFF extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, new FindFluffyFriendFragment()).commit();
        }
    }
}
