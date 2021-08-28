package com.example.tindog.ui.fff;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tindog.data.User;
import com.example.tindog.databinding.FragmentFindFluffyFriendItemBinding;
//import com.example.tindog.databinding.FragmentFindFluffyFriendListBinding;
import com.squareup.picasso.Picasso;


import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link User}.
 * TODO: Replace the implementation with code for your data type.
 */
public class FriendRecyclerViewAdapter extends RecyclerView.Adapter<FriendRecyclerViewAdapter.ViewHolder> {

    private final List<User> mValues;

    public FriendRecyclerViewAdapter(List<User> items) {
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
        Picasso.get().load(holder.mItem.pic).into(holder.mImg);
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

        public ViewHolder(FragmentFindFluffyFriendItemBinding binding) {
            super(binding.getRoot());

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