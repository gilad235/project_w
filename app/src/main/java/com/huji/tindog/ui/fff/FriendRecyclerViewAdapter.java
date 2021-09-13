package com.huji.tindog.ui.fff;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huji.tindog.CurrentUserDetails;
import com.huji.tindog.R;
import com.huji.tindog.data.User;
import com.huji.tindog.databinding.FragmentFindFluffyFriendItemBinding;
//import com.example.tindog.databinding.FragmentFindFluffyFriendListBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link User}.
 * TODO: Replace the implementation with code for your data type.
 */
public class FriendRecyclerViewAdapter extends RecyclerView.Adapter<FriendRecyclerViewAdapter.ViewHolder> {
    String keyToDelete;

    private final List<User> mValues;

    public FriendRecyclerViewAdapter(List<User> items) {
//        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentFindFluffyFriendItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mNameView.setText(holder.mItem.name);
        holder.mDogNameView.setText(holder.mItem.dogName);


        String UUIDToDelete = holder.mItem.getId();
        holder.deleteButton.setOnClickListener(view -> {

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
            DatabaseReference userQuery = ref.child("users");
            DatabaseReference myRef = userQuery.child(CurrentUserDetails.getInstance().getUserID());

            DatabaseReference myRef_1 = myRef.child("friends");
            myRef_1.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    } else {
                        Log.d("firebase", String.valueOf(task.getResult().getValue()));
                        for (DataSnapshot snap : task.getResult().getChildren()) {
                            User cur_user = snap.getValue(User.class);
                            if (cur_user.getId().equals(UUIDToDelete)) {

                                keyToDelete = snap.getKey();
                                break;
                            }
                        }
                        myRef_1.child(keyToDelete).setValue(null);
                        notifyDataSetChanged();

                    }
                }
            });






        this.notifyDataSetChanged();

        });

        if(holder.mItem.pic!=null&&!holder.mItem.pic.equals("")){
        Picasso.get().load(holder.mItem.pic).into(holder.mImg);
        }
        Picasso.get().load("https://image.flaticon.com/icons/png/512/194/194279.png").into(holder.mDogImg);
        }

@Override
public int getItemCount(){
        return mValues.size();
        }

public class ViewHolder extends RecyclerView.ViewHolder {
    public final ImageView mImg;
    public final ImageView mDogImg;
    public final TextView mNameView;
    public final TextView mDogNameView;
    public User mItem;
    public FloatingActionButton deleteButton;


    public ViewHolder(FragmentFindFluffyFriendItemBinding binding) {
        super(binding.getRoot());

        this.deleteButton = itemView.findViewById(R.id.removeFriend);
        mImg = binding.userImg;
        mDogImg = binding.userDogPic;
        mNameView = binding.userName;
        mDogNameView = binding.dogName;
    }

    @Override
    public String toString() {
        return super.toString() + " '" + mNameView.getText() + "'";
    }
}
}