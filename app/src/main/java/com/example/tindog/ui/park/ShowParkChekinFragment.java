package com.example.tindog.ui.park;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.tindog.R;

public class ShowParkChekinFragment extends Fragment {
        public String parkName;


        public ShowParkChekinFragment() {
            // Required empty public constructor
        }


//        // TODO: Rename and change types and number of parameters
        public static com.example.tindog.ui.park.ShowParkChekinFragment newInstance(String param1) {
            com.example.tindog.ui.park.ShowParkChekinFragment fragment = new com.example.tindog.ui.park.ShowParkChekinFragment();
            Bundle args = new Bundle();
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                parkName = getArguments().getString("parkName");
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.show_park_checking_fragment, container, false);
        }
    }

