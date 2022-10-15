package com.midterm.project.touristguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LandmarkAdapter extends RecyclerView.Adapter<LandmarkAdapter.LandMarkViewHolder> {
    private Context mContext;
    private List<Landmark> mListLandmark;

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
        return new LandMarkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LandMarkViewHolder holder, int position) {
        Landmark landmark = mListLandmark.get(position);
        if(landmark == null){
            return;
        }
        holder.imgLandmark.setImageResource(landmark.getResourceImage());
        holder.name.setText(landmark.getName());
        holder.description.setText(landmark.getDescription());
        holder.rating.setText(landmark.getRating());
    }

    @Override
    public int getItemCount() {
        if(mListLandmark != null){
            return mListLandmark.size();
        }
        return 0;
    }

    public class LandMarkViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgLandmark;
        private TextView name;
        private TextView description;
        private TextView rating;

        public LandMarkViewHolder(@NonNull View itemView) {
            super(itemView);

            imgLandmark = itemView.findViewById(R.id.image_landmark);
            name = itemView.findViewById(R.id.tv_name);
            description = itemView.findViewById(R.id.tv_description);
            rating = itemView.findViewById(R.id.rating);
        }
    }
}
