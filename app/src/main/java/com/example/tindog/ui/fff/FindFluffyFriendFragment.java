package com.example.tindog.ui.fff;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tindog.R;

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
        return inflater.inflate(R.layout.fragment_find_fluffy_friend, container, false);
    }
}