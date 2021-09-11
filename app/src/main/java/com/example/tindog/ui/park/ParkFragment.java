package com.example.tindog.ui.park;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tindog.R;
import com.example.tindog.data.Park;
import com.example.tindog.data.User;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ParkFragment extends Fragment {

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            DatabaseReference database = FirebaseDatabase.getInstance().getReference();
            DatabaseReference myRef = database.child("parks");
//            onTimeAddParks();
//
//            myRef.addChildEventListener(new ChildEventListener() {
//                // TODO: implement the ChildEventListener methods as documented above
//                // [START_EXCLUDE]
//                @Override
//                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                    Log.i("firebase", "child onChildAdded");
//                }
//
//                @Override
//                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                    Log.i("firebase", "child onChildChanged");
//
//                }
//
//                @Override
//                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//                    Log.i("firebase", "child onChildRemoved");
//
//                }
//
//                @Override
//                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                    Log.i("firebase", "child onChildMoved");
//
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//                    Log.i("firebase", "on cancelled");
//
//                }
//                // [END_EXCLUDE]
//            });



            myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    }
                    else {
                        Log.d("firebase", String.valueOf(task.getResult().getValue()));

                        for (DataSnapshot snapshot : task.getResult().getChildren()) {
                            Park park = snapshot.getValue(Park.class);
                            LatLng parkLoc = new LatLng(park.x, park.y);
                            googleMap.addMarker(new MarkerOptions().position(parkLoc).title(park.name));
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(parkLoc, 12.0f));
                        }
                    }
                }
            });

            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    if (marker!=null){
                        Intent intent = new Intent(getActivity(), parkCheckInActivity.class);
                        intent.putExtra("parkName",marker.getTitle());
                        startActivity(intent);


                        return true;
                    }
                    return false;
                }}

                );

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_park, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }


//    public void onTimeAddParks(){
//        ArrayList<Park> parks = new ArrayList<>();
//        parks.add(new Park("Gan Meir",(float)32.0731,(float)34.7709533,new ArrayList<User>()));
//        parks.add(new Park("Kiryat Sefer Park",(float)32.069922,(float)34.7775208,new ArrayList<User>()));
//        parks.add(new Park("Mordehai Garden",(float)32.069497,(float)34.772995,new ArrayList<User>()));
//        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
//
//        for(Park p: parks){
//            db.child("parks").child(p.name).setValue(p);
//
//
//        }



    }
