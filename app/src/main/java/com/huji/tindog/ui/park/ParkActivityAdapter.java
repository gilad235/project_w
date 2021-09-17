package com.huji.tindog.ui.park;

import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.huji.tindog.CurrentUserDetails;
import com.huji.tindog.R;
import com.huji.tindog.data.Park;
import com.huji.tindog.data.User;
import com.huji.tindog.databinding.ShowParkCheckingItemBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class ParkActivityAdapter extends RecyclerView.Adapter<ParkActivityAdapter.AdapterViewHolder> {

    private final DataHolder localDataSet;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    static class AdapterViewHolder extends RecyclerView.ViewHolder {

        TextView mUserPhone;
        ImageView mDogImg;
        TextView mNameView;
        TextView mDogNameView;
        TextView mlastSeen;
        User mItem;
        FloatingActionButton addFriend;


        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            mUserPhone = itemView.findViewById(R.id.user_phone);
            mDogImg = itemView.findViewById(R.id.user_dog_pic);
            mNameView = itemView.findViewById(R.id.user_name);
            mDogNameView = itemView.findViewById(R.id.dog_name);
            mlastSeen = itemView.findViewById(R.id.last_seen);
            addFriend = itemView.findViewById(R.id.add_friend_button);


        }
    }


    /**
     * Initialize the dataset of the Adapter.
     *
     * @param curTasks א containing the data to populate views to be used
     *                 by RecyclerView.
     */
    public ParkActivityAdapter( DataHolder curTasks) {

        localDataSet = curTasks;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.show_park_checking_item, viewGroup, false);

        return new AdapterViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {

        checkInWarrper cur = localDataSet.getCurrentItems().get(position);
        holder.mItem = cur.user;

        if (cur.getEditDate() > 60) {
            localDataSet.deleteItem(holder.mItem);
            this.notifyDataSetChanged();
        }
        holder.mNameView.setText("Owner Name : " + holder.mItem.name);
        holder.mDogNameView.setText("Dog Name : " + holder.mItem.dogName);
        holder.mUserPhone.setText("Owner Phone : " + holder.mItem.phone);


        String title = "User last seen " + String.valueOf(cur.getEditDate());
        holder.mlastSeen.setText(title + " Minute ago ");

        Picasso.get().load("https://image.flaticon.com/icons/png/512/194/194279.png").into(holder.mDogImg);
        holder.addFriend.setOnClickListener(v -> {
            if(!cur.user.id.equals(CurrentUserDetails.getInstance().getUserID())){
                DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                DatabaseReference myRef = database.child("users").child(CurrentUserDetails.getInstance().getCurUser().id).child("friends");
                myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (!task.isSuccessful()) {
                            Log.e("firebase", "Error getting data", task.getException());
                        } else {
                            Log.d("firebase", String.valueOf(task.getResult().getValue()));
                            boolean user_exist_flag = false;

                            for (DataSnapshot snapshot : task.getResult().getChildren()) {
                                User user = snapshot.getValue(User.class);
                                if (user.id.equals(holder.mItem.id)) {
                                    user_exist_flag = true;
                                    break;
                                }
                            }
                            if (!user_exist_flag) {
                                myRef.push().setValue(holder.mItem);
                            }

                        }
                    }

                });


            }

        });
    }





    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.getCurrentItems().size();
    }


}




