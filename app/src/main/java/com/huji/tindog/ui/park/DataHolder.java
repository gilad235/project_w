package com.huji.tindog.ui.park;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.huji.tindog.data.Park;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class DataHolder {
    ArrayList<checkInWarrper> cur_checkins;

    private static Context myContext =null;
    private static SharedPreferences pref;
    private static final MutableLiveData<List<checkInWarrper>> myMutable = new MutableLiveData<>();
    public static final LiveData<List<checkInWarrper>> myLiveDate=myMutable;
    private String parkName;
    private final DatabaseReference db;

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected DataHolder(Context context,String parkName){
        cur_checkins =new ArrayList<>();
        this.parkName = parkName;
        myContext=context;
        db = FirebaseDatabase.getInstance().getReference();
        pref = context.getSharedPreferences("local_db_app",Context.MODE_PRIVATE);
        set_mutable();

        // TODO get the park name

    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    public void set_mutable(){

        DatabaseReference myRef = db.child("parks");

        myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    for (DataSnapshot snapshot : task.getResult().getChildren()) {
                        Objects.requireNonNull(snapshot.getValue(Park.class));
                        if (Objects.requireNonNull(snapshot.getValue(Park.class)).name.equals(parkName)){

                            ArrayList<checkInWarrper> tempUsers= Objects.requireNonNull(snapshot.getValue(Park.class)).checkins;
                            if (tempUsers !=null){
                                ArrayList<checkInWarrper> filterd = new ArrayList<>();
                                Date c = Calendar.getInstance().getTime();

                                for(checkInWarrper wap :tempUsers){
                                    if ( (c.getTime()-wap.time.getTime())/(60*60*1000)>2){
                                        continue;
                                    }
                                    filterd.add(wap);

                                }
                                cur_checkins=filterd;
                                myMutable.setValue(getCurrentItems());
                            }
                        }


                    }

                }
            }
        });







    }

    public void add_item(checkInWarrper item){

        // add new checking
        if(!cur_checkins.contains(item)){
            cur_checkins.add(item);
            myMutable.setValue(getCurrentItems());
            updateDbParkCheckin();
        }







    }

//    public User getItem(String curnum){
//        for (checkInWarrper todo:cur_checkins) {
//            if (todo.user.id.equals(curnum)){
//                return todo;
//
//            }
//
//        }
//
//        return null;
//    }

    public LiveData<List<checkInWarrper>> getLiveData() {
        return myLiveDate;
    }





    public ArrayList<checkInWarrper> getCurrentItems() {


        return new ArrayList<checkInWarrper>(cur_checkins); }

    private void updateDbParkCheckin(){
        db.child("parks").child(parkName).child("checkins").setValue(getCurrentItems());


    }




//    public void sendBroadCastDBchanges(){
//        Intent brod = new Intent("db_changed");
//        brod.putExtra("new_list", (Parcelable) this.getCurrentItems());
//        myContext.sendBroadcast(brod);
//    }



    public void deleteItem(checkInWarrper item) {
        if(cur_checkins.contains(item)){
            cur_checkins.remove(item);
            myMutable.setValue(getCurrentItems());
            updateDbParkCheckin();
        }



    }





}



