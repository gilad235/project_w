package com.example.tindog.ui.login;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tindog.CurrentUserDetails;
import com.example.tindog.MainActivity;
import com.example.tindog.R;
import com.example.tindog.ui.fff.AddNewFriend;
import com.example.tindog.ui.tinder.AddNewDog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link com.example.tindog.ui.fff.FindFluffyFriendFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogOutFragment extends Fragment {


    public LogOutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FindFluffyFriendFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static com.example.tindog.ui.fff.FindFluffyFriendFragment newInstance(String param1, String param2) {
        com.example.tindog.ui.fff.FindFluffyFriendFragment fragment = new com.example.tindog.ui.fff.FindFluffyFriendFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_out, container, false);
        Button logout_button = (Button) view.findViewById(R.id.signOut);


        logout_button.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(v.getContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        });

        return view;
    }
}
