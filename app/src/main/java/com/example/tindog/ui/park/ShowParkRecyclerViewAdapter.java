package com.example.tindog.ui.park;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tindog.R;
import com.example.tindog.data.User;
import com.example.tindog.databinding.ShowParkCheckingItemBinding;
import com.example.tindog.ui.fff.FriendRecyclerViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ShowParkRecyclerViewAdapter extends RecyclerView.Adapter<com.example.tindog.ui.park.ShowParkRecyclerViewAdapter.ViewHolder> {

        private final List<User> mValues;
     public String parkName;


    public ShowParkRecyclerViewAdapter(List<User> items) {
            mValues = items;
        }

        @NonNull
        @Override
        public com.example.tindog.ui.park.ShowParkRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//            this.parkName = parent.parkName;

            return new ViewHolder(ShowParkCheckingItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));


        }

        @Override
        public void onBindViewHolder(final com.example.tindog.ui.park.ShowParkRecyclerViewAdapter.ViewHolder holder, int position) {
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

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public final ImageView mImg;
            public final ImageView mDogImg;
            public final TextView mNameView;
            public final TextView mDogNameView;
            public User mItem;

            public ViewHolder(ShowParkCheckingItemBinding binding) {
                super(binding.getRoot());

                mImg = binding.userImg;
                mDogImg = binding.userDogPic;
                mNameView = binding.userName;
                mDogNameView = binding.dogName;
            }



            @NonNull
            @Override
            public String toString() {
                return super.toString() + " '" + mNameView.getText() + "'";
            }
        }
    }

