package com.example.tindog.ui.park;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.tindog.R;
import com.example.tindog.data.User;
import com.example.tindog.databinding.ShowParkCheckingItemBinding;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Date;
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


        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            mUserPhone = itemView.findViewById(R.id.user_phone);
            mDogImg = itemView.findViewById(R.id.user_dog_pic);
            mNameView = itemView.findViewById(R.id.user_name);
            mDogNameView = itemView.findViewById(R.id.dog_name);
            mlastSeen = itemView.findViewById(R.id.last_seen);


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

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
//        User cur_item = localDataSet.getCurrentItems().get(position);
//        holder.description.setText(cur_item.getDescription());
//        holder.Status.setText(cur_item.getStatusdes());
//        holder.root1.setText("Got until: "+cur_item.last);
//        holder.root2.setText("...");
//        holder.mainview.setBackgroundColor(Color.BLUE);
//        holder.pbar.setProgress((int)cur_item.progress);
        Date c = Calendar.getInstance().getTime();

        if ((c.getTime()-localDataSet.getCurrentItems().get(position).time.getTime())/(60*60*1000)>1){
            localDataSet.deleteItem(localDataSet.getCurrentItems().get(position));
            this.notifyDataSetChanged();
        }
        holder.mItem = localDataSet.getCurrentItems().get(position).user;
        holder.mNameView.setText("Owner Name : "+holder.mItem.name);
        holder.mDogNameView.setText("Dog Name : "+holder.mItem.dogName);
        holder.mUserPhone.setText("Owner Phone : "+holder.mItem.phone);
        long diffInMillies =((c.getTime()-localDataSet.getCurrentItems().get(position).time.getTime())/(60*1000));
        long diff = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);

        String title= "User last seen "+ String.valueOf(diff);
        holder.mlastSeen.setText(title+ " Minute ago ");

        Picasso.get().load("https://image.flaticon.com/icons/png/512/194/194279.png").into(holder.mDogImg);


//        holder.deleteButton.setOnClickListener(v ->
//
//                {
//                    SingeltonApp.getInstance().getMyworker().cancelWorkById(cur_item.ID);
//                    this.localDataSet.deleteItem(cur_item);
//
//                    this.notifyDataSetChanged();
//
//                }
//        );


    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.getCurrentItems().size();
    }


}




