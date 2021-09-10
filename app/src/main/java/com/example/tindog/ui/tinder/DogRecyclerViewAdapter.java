package com.example.tindog.ui.tinder;

import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.tindog.data.Dog;
import com.example.tindog.databinding.FragmentDogItemBinding;
import com.example.tindog.ui.dogform.DogFormActivity;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * {@link RecyclerView.Adapter} that can display a {@link Dog}.
 * TODO: Replace the implementation with code for your data type.
 */
public class DogRecyclerViewAdapter extends RecyclerView.Adapter<DogRecyclerViewAdapter.ViewHolder> {

    private  List<Dog> mValues;

    public DogRecyclerViewAdapter(List<Dog> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentDogItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        Dog item =  mValues.get(position);
        holder.mNameView.setText(item.name);
        if (item.male) {
            holder.mGenderView.setText("Male");
        } else {
            holder.mGenderView.setText("Female");
        }
        holder.mAgeView.setText(item.age + " years old");
        Picasso.get().load(item.pic).into(holder.mImg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();

                Intent intent = new Intent(context, DogDetailsActivity.class);
                intent.putExtra("dog", holder.mItem);
                context.startActivity(intent);
//                ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
//                ClipData clip = ClipData.newPlainText("label", item.phone);
//                clipboard.setPrimaryClip(clip);
//                Snackbar.make(view, "Phone number: " + item.phone + " copied to clipboard", Snackbar.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mNameView;
        public final TextView mGenderView;
        public final TextView mAgeView;
        public final ImageView mImg;
        public Dog mItem;

        public ViewHolder(FragmentDogItemBinding binding) {
            super(binding.getRoot());
            mNameView = binding.name;
            mGenderView = binding.gender;
            mAgeView = binding.age;
            mImg = binding.img;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNameView.getText() + "'";
        }
    }




}