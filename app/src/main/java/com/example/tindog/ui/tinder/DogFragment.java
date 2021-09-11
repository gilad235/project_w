package com.example.tindog.ui.tinder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.tindog.R;
import com.example.tindog.ui.fff.FindFluffyFriendFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

public class DogFragment extends Fragment {
    View ui;
    LayoutInflater inflater;
    ViewGroup container;

    public DogFragment() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DogFragment newInstance(String param1, String param2) {
        DogFragment fragment = new DogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public static Fragment newInstance() {
        DogFragment fragment = new DogFragment();
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
    public void onDestroyView() {
        super.onDestroyView();
        Fragment f = (Fragment) getFragmentManager().findFragmentById(R.id.list_dogs);
        if (f != null){
            getFragmentManager().beginTransaction().remove(f).commit();
        }
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//    ui = inflater.inflate(R.layout.fragment_dog, container, false);
//    this.inflater=inflater;
//    this.container=container;
//    return ui;
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        if (ui != null) {
//            ViewGroup parent = (ViewGroup) ui.getParent();
//            if (parent != null) {
//                parent.removeView(ui);
//            }
//        }
//
//        try {
//            View ui = inflater.inflate(R.layout.fragment_dog, container, false);
//
//        } catch (InflateException e) {
//            Log.i("dog fragment", "HERE COMES THE STACK TRACE");
//            e.printStackTrace();
//        }
//        FloatingActionButton add_button = (FloatingActionButton) ui.findViewById(R.id.addFriend);
//        FloatingActionButton filter_button = (FloatingActionButton) ui.findViewById(R.id.filter);
//        add_button.setOnClickListener(v -> {
//            Intent intentToOpenAddDog = new Intent(v.getContext(), AddNewDog.class);
//            v.getContext().startActivity(intentToOpenAddDog);
//
//        });
//
//        filter_button.setOnClickListener(v -> {
////            closefragment();
//            Intent intentToOpenAddDog = new Intent(v.getContext(), TindogSetting.class);
////            getFragmentManagerManager().beginTransaction().remove(new DogFragment()).commit();
//            v.getContext().startActivity(intentToOpenAddDog);
////            reloadFragment();
//
//        });
//    }

            View view = inflater.inflate(R.layout.fragment_dog, container, false);
        FloatingActionButton add_button = (FloatingActionButton) view.findViewById(R.id.addFriend);
        FloatingActionButton filter_button = (FloatingActionButton) view.findViewById(R.id.filter);
        add_button.setOnClickListener(v -> {
            Intent intentToOpenAddDog = new Intent(v.getContext(), AddNewDog.class);
            v.getContext().startActivity(intentToOpenAddDog);

        });

        filter_button.setOnClickListener(v -> {
//            closefragment();
            Intent intentToOpenAddDog = new Intent(v.getContext(), TindogSetting.class);
//            getFragmentManagerManager().beginTransaction().remove(new DogFragment()).commit();
            v.getContext().startActivity(intentToOpenAddDog);
//            reloadFragment();

        });
        return view;


    }
    public void reloadFragment(){
        Fragment curFrg = getParentFragmentManager().findFragmentByTag("dogs");
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.detach(curFrg);
        ft.attach(curFrg);
        ft.commit();

    }
    private void closefragment() {
        getActivity().getFragmentManager().popBackStack();    }

}
