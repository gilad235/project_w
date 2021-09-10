package com.example.tindog.ui.fff;

import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tindog.R;
import com.example.tindog.data.User;
import com.example.tindog.databinding.FragmentFindFluffyFriendItemBinding;
//import com.example.tindog.databinding.FragmentFindFluffyFriendListBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link User}.
 * TODO: Replace the implementation with code for your data type.
 */
public class FriendRecyclerViewAdapter extends RecyclerView.Adapter<FriendRecyclerViewAdapter.ViewHolder> {

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


        String UUID=holder.mItem.getId();
//        holder.deleteButton.setOnClickListener(view -> {
//            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
//            DatabaseReference userQuery = ref.child("users").child("friends").child();
//            userQuery.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    for (DataSnapshot snap: dataSnapshot.getChildren()) {
//                        snap.getRef().removeValue();
//                    }
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//                    Log.e("delete button", "onCancelled", databaseError.toException());
//                }
//            });
//            this.notifyDataSetChanged();
//
//        });

        if (holder.mItem.pic!=null && !holder.mItem.pic.equals("")  ){
        Picasso.get().load(holder.mItem.pic).into(holder.mImg);
        }
        Picasso.get().load("https://image.flaticon.com/icons/png/512/194/194279.png").into(holder.mDogImg);
    }

    @Override
    public int getItemCount() {
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

            this.deleteButton=itemView.findViewById(R.id.removeFriend);
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