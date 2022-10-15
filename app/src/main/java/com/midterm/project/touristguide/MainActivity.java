package com.midterm.project.touristguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvcLandmark;
    private LandmarkAdapter mLandmarkAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvcLandmark = findViewById(R.id.rcv_landmark);
        mLandmarkAdapter = new LandmarkAdapter(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        rvcLandmark.setLayoutManager(gridLayoutManager);

        mLandmarkAdapter.setData(getListLandmark());
        rvcLandmark.setAdapter(mLandmarkAdapter);
    }

    private List<Landmark> getListLandmark() {
        List<Landmark> list = new ArrayList<>();
        list.add(new Landmark(R.drawable.picture_1,"Indedenpence Palace","asddsdfasdfasdfadsfadsfasdfsadfsadfadsfsdafadsfsdfsdfasdfsdfasdfasdfasdasdfsadfdfdvdvrwefdsffrdudhaduifhidufaha8rehhdfiohdfhadsfhfhewf8eh8hewfdhfhfd","5"));
        list.add(new Landmark(R.drawable.picture_2,"picture2","sdffdfsdfdfsadasda","2"));
        list.add(new Landmark(R.drawable.picture_3,"picture3","efvc","4"));
        list.add(new Landmark(R.drawable.picture_4,"picture4","ads","3"));

        return list;
    }
}