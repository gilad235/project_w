package com.example.tindog.ui.tinder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tindog.CurrentUserDetails;
import com.example.tindog.R;
import com.example.tindog.data.Dog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class DogListFragment extends Fragment {


    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    //todo need to make sure the setting activity and this activity share the same filter
    private FilterData myFilter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DogListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static DogListFragment newInstance(int columnCount) {
        DogListFragment fragment = new DogListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.myFilter = CurrentUserDetails.getInstance().userFilter;

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dog_list, container, false);





        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }



            DatabaseReference database = FirebaseDatabase.getInstance().getReference();
            DatabaseReference myRef = database.child("dogs");
            myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    } else {
                        Log.d("firebase", String.valueOf(task.getResult().getValue()));
                        List<Dog> items = new ArrayList<Dog>();
                        for (DataSnapshot snapshot : task.getResult().getChildren()) {

                            Dog dog = snapshot.getValue(Dog.class);
                            if (dog==null){continue;}
                            if ((myFilter.getCastrated()==Choice.both || (myFilter.getCastrated()==Choice.first && dog.castrated)||
                                    (myFilter.getCastrated()==Choice.second && !dog.castrated))&& //castrated matches filter
                                    (myFilter.getGender()==Choice.both || (myFilter.getGender()==Choice.first && dog.male)||
                                            (myFilter.getGender()==Choice.second && !dog.male))&&//gender matches filter
                                    (myFilter.getMaxAge()>=dog.age && myFilter.getMinAge()<=dog.age)//age matches filter
                            )
                            {
                                items.add(dog);
                            }
                        }

                        recyclerView.setAdapter(new DogRecyclerViewAdapter(items));
                    }
                }
            });


        }

        return view;
    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        View view1 = inflater.inflate(R.layout.fragment_dog, container, false);
//
//        Button add_button = (Button) view1.findViewById(R.id.addFriend);
//
//        add_button.setOnClickListener(v -> {
//            System.out.println("hellooooooooo");
//            String userInputString = comment.getText().toString();
//            String customerName=name.getText().toString();
//            int picklesNum=pickleNumber.getValue();
//            dataBase.addNewOrder(picklesNum,tahiniStatus,hummusStatus,userInputString,customerName);
//            Intent intentToOpenEdit = new Intent(v.getContext(), EditOrderActivity.class);
//            finish();
//            v.getContext().startActivity(intentToOpenEdit);

//        });
//
//
//        return view1;
//
//    }
}