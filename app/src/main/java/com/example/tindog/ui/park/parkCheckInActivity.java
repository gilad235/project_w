package com.example.tindog.ui.park;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tindog.CurrentUserDetails;
import com.example.tindog.R;
import com.example.tindog.data.User;
import com.example.tindog.databinding.ActivityDogDetailsBinding;
import com.example.tindog.databinding.ActivityDogFormBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class parkCheckInActivity extends AppCompatActivity {

    public DataHolder holder = null;
    public LinearLayoutManager layoutManager = null;
    ParkActivityAdapter cur_adapter;
    public String parkName;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_park_checking_fragment);
        parkName=getIntent().getStringExtra("parkName");
        if (holder == null) {
            // remove and make static
            holder = new DataHolder(this,parkName);
        }

        if (cur_adapter == null) {
            cur_adapter = new ParkActivityAdapter(holder);
        }
        FloatingActionButton createNewTask = findViewById(R.id.buttonCreateTodoItem);
        FloatingActionButton checkout = findViewById(R.id.checkout);
        RecyclerView recyclerList = findViewById(R.id.recyclerTodoItemsList);
        recyclerList.setAdapter(cur_adapter);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerList.setLayoutManager(layoutManager);
        createNewTask.setEnabled(true);


//        loadjobs();

        holder.getLiveData().observe(this, new Observer<List<User>>() {


            @Override
            public void onChanged(List<User> todoItems) {
                {
                    cur_adapter.notifyDataSetChanged();

                }
            }



        });

        createNewTask.setOnClickListener(v -> {

                        holder.add_item(CurrentUserDetails.getInstance().getCurUser());


                        // make the rcycler show this first
                        cur_adapter.notifyDataSetChanged();



                }
        );


        checkout.setOnClickListener(v -> {

            holder.deleteItem(CurrentUserDetails.getInstance().getCurUser());

            // make the rcycler show this first
            cur_adapter.notifyDataSetChanged();



        });
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();


        DatabaseReference docRef =  db.child("parks").child(parkName);

        docRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) {
                Log.d("Data onChildAdded", dataSnapshot.getValue().toString());


                //Toast.makeText(getBaseContext(), "data=" + dataSnapshot.getValue(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, String s) {
                Log.d("Data onChildChanged", dataSnapshot.getValue().toString());
                holder.set_mutable();
                cur_adapter.notifyDataSetChanged();
                //Toast.makeText(getBaseContext(), "data=" + dataSnapshot.getValue(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Log.d("Data onChildRemoved", dataSnapshot.getValue().toString());
                //Toast.makeText(getBaseContext(), "data=" + dataSnapshot.getValue(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, String s) {
                Log.d("Data onChildMoved", dataSnapshot.getValue().toString());
                //Toast.makeText(getBaseContext(), "data=" + dataSnapshot.getValue(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("error", error.toString());


            }


        });





        }


}
