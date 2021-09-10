package com.example.tindog.ui.park;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tindog.R;
import com.example.tindog.data.Park;
import com.example.tindog.data.User;
import com.example.tindog.ui.fff.FriendRecyclerViewAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * A fragment representing a list of Items.
 */
public class ShowParkCheckinListFragment extends Fragment {


    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private String parkName;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ShowParkCheckinListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static com.example.tindog.ui.park.ShowParkCheckinListFragment newInstance(int columnCount) {
        com.example.tindog.ui.park.ShowParkCheckinListFragment fragment= new com.example.tindog.ui.park.ShowParkCheckinListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ShowParkChekinFragment frag = (ShowParkChekinFragment) getParentFragment();
//        assert frag != null;
//        this.parkName = frag.parkName;





        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.show_park_checking_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            // todo - the checkins , different child?
            DatabaseReference database = FirebaseDatabase.getInstance().getReference();
            DatabaseReference myRef = database.child("parks");
            myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    } else {
                        Log.d("firebase", String.valueOf(task.getResult().getValue()));
                        List<User> items = new ArrayList<User>();
                        for (DataSnapshot snapshot : task.getResult().getChildren()) {
                            if (Objects.requireNonNull(snapshot.getValue(Park.class)).name.equals(parkName))
                                items.addAll(Objects.requireNonNull(snapshot.getValue(Park.class)).checkins);
                        }

                        recyclerView.setAdapter(new FriendRecyclerViewAdapter(items));
                    }
                }
            });
        }
        return view;
    }
}