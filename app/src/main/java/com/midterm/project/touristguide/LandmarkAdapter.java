package com.midterm.project.touristguide;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class LandmarkAdapter extends RecyclerView.Adapter<LandmarkAdapter.LandMarkViewHolder> {
    private Context mContext;
    private List<Landmark> mListLandmark;
    private List<String> mFavouriteList;
    public LandmarkAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void setData(List<Landmark> list){
        this.mListLandmark = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public LandMarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_landmark,parent,false);
        View view_favourite = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_favourite_list,parent,false);
        return new LandMarkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LandMarkViewHolder holder, int position) {
        Landmark landmark = mListLandmark.get(position);
        if(landmark == null){
            return;
        }
        holder.imgLandmark.setImageURI(landmark.getResourceImage()[0]);
        holder.name.setText(landmark.getName());
        holder.description.setText(landmark.getDescription());
        holder.rating.setText("Rating:"+String.valueOf(landmark.getRating()) + "/5");



        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,LandmarkDetail.class);
                ArrayList<Uri> arrayList = new ArrayList<>(Arrays.asList(landmark.getResourceImage()));
                intent.putExtra("imagesUri", Arrays.toString(landmark.getResourceImage()));
                intent.putParcelableArrayListExtra("imagesUri",arrayList);
                intent.putExtra("detailName",landmark.getName());
                intent.putExtra("detailDescription",landmark.getDescription());
                intent.putExtra("detailPhone",landmark.getPhoneNumber());
                intent.putExtra("detailLocation",landmark.getAddress());
                intent.putExtra("detailWiki",landmark.getWikipage());
                mContext.startActivity(intent);
            }

        });

        holder.layoutItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                landmark.setFavourite(!landmark.isFavourite());
                Toast.makeText(mContext.getApplicationContext(),landmark.toString() , Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mListLandmark != null){
            return mListLandmark.size();
        }
        return 0;
    }

    public class LandMarkViewHolder extends RecyclerView.ViewHolder {
        private CardView layoutItem;
        private ImageView imgLandmark;
        private TextView name;
        private TextView description;
        private TextView rating;

        public LandMarkViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutItem = itemView.findViewById(R.id.layoutItem);
            imgLandmark = itemView.findViewById(R.id.image_landmark);
            name = itemView.findViewById(R.id.tv_name);
            description = itemView.findViewById(R.id.tv_description);
            rating = itemView.findViewById(R.id.rating);
        }
    }
}
