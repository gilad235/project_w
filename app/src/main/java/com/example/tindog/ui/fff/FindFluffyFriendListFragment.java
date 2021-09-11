package com.example.tindog.ui.fff;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tindog.CurrentUserDetails;
import com.example.tindog.R;
import com.example.tindog.data.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class FindFluffyFriendListFragment extends Fragment {
    View view;
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FindFluffyFriendListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static FindFluffyFriendListFragment newInstance(int columnCount) {
        FindFluffyFriendListFragment fragment = new FindFluffyFriendListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_find_fluffy_friend_list, container, false);

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
            DatabaseReference myRef = database.child("users");
            myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    } else {
                        Log.d("firebase", String.valueOf(task.getResult().getValue()));
//                        List<User> items = new ArrayList<>();
                        for (DataSnapshot snapshot : task.getResult().getChildren()) {
                            User user = snapshot.getValue(User.class);
                            if (user.getId().equals( CurrentUserDetails.getInstance().getUserID())) {
//                                items=user.getFriends();
                                DatabaseReference myRef1 = myRef.child(user.getId());
                                DatabaseReference myRef2 = myRef1.child("friends");
                                myRef2.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        List<User> items = new ArrayList<>();

                                        for (DataSnapshot postSnapshot: snapshot.getChildren()){
                                            User user = postSnapshot.getValue(User.class);
                                            items.add(user);
                                        }
                                        recyclerView.setAdapter(new FriendRecyclerViewAdapter(items));

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });



                            }
                        }

//                        recyclerView.setAdapter(new FriendRecyclerViewAdapter(items));
                    }
                }
            });
        }
        return view;
    }

    @Override
    public void onResume() {
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
        super.onResume();


            DatabaseReference database = FirebaseDatabase.getInstance().getReference();
            DatabaseReference myRef = database.child("users");
            myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    } else {
                        Log.d("firebase", String.valueOf(task.getResult().getValue()));
//                        List<User> items = new ArrayList<>();
                        for (DataSnapshot snapshot : task.getResult().getChildren()) {
                            User user = snapshot.getValue(User.class);
                            if (user.getId().equals( CurrentUserDetails.getInstance().getUserID())) {
//                                items=user.getFriends();
                                DatabaseReference myRef1 = myRef.child(user.getId());
                                DatabaseReference myRef2 = myRef1.child("friends");
                                myRef2.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        List<User> items = new ArrayList<>();

                                        for (DataSnapshot postSnapshot: snapshot.getChildren()){
                                            User user = postSnapshot.getValue(User.class);
                                            items.add(user);
                                        }
                                        recyclerView.setAdapter(new FriendRecyclerViewAdapter(items));

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });



                            }
                        }

//                        recyclerView.setAdapter(new FriendRecyclerViewAdapter(items));
                    }
                }
            });
        }
    }
}