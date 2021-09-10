package com.example.tindog.ui.fff;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tindog.CurrentUserDetails;
import com.example.tindog.MainActivity;
import com.example.tindog.R;
import com.example.tindog.ui.tinder.AddNewDog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FindFluffyFriendFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FindFluffyFriendFragment extends Fragment {


    public FindFluffyFriendFragment() {
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
    public static FindFluffyFriendFragment newInstance(String param1, String param2) {
        FindFluffyFriendFragment fragment = new FindFluffyFriendFragment();
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
        View view=inflater.inflate(R.layout.fragment_find_fluffy_friend, container, false);
        FloatingActionButton add_button = (FloatingActionButton) view.findViewById(R.id.add_Friend);
        FloatingActionButton remove_button = (FloatingActionButton) view.findViewById(R.id.removeFriend);

        add_button.setOnClickListener(v -> {
            Intent intentToOpenAddDog = new Intent(v.getContext(), AddNewFriend.class);
            v.getContext().startActivity(intentToOpenAddDog);

        });

        try {
            System.out.println(CurrentUserDetails.getInstance().getUserID());


        } catch (Exception e){
            System.out.println("catch");

        }

        return view;
    }
}