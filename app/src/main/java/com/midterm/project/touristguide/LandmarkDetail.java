package com.midterm.project.touristguide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class LandmarkDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landmark_detail);
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        Landmark landmark = (Landmark) bundle.get("object_landmark");
        ImageView imageView = findViewById(R.id.landmarkDetailImage);
        TextView name = findViewById(R.id.detailName);
        imageView.setImageResource(landmark.getResourceImage());
        name.setText(landmark.getName());
    }
}