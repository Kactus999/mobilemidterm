package com.midterm.project.touristguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
        list.add(new Landmark(R.drawable.picture_1,"Indedenpence Palace","This is the place marking the complete victory of anti-American resistance war, libration of the South and national reunification.",5));
        list.add(new Landmark(R.drawable.picture_2,"Notre-Dame Cathedral Basilica of Saigon","Established by French colonists who initially named it the Church of Saigon (French: l'Eglise de Saïgon), the cathedral was constructed between 1863 and 1880. The name Notre-Dame Cathedral has been used since 1959. It has two bell towers, reaching a height of 58 meters",3));
        list.add(new Landmark(R.drawable.picture_3,"Bến Thành Market","Bến Thành Market (Vietnamese: Chợ Bến Thành) is located in the center of Hồ Chí Minh City, Vietnam in District 1. The market is one of the earliest surviving structures in Saigon and an important symbol of the city.",4));
        list.add(new Landmark(R.drawable.picture_4,"Municipal Theatre","The Municipal Theatre of Ho Chi Minh City, also known as Saigon Municipal Opera House (Vietnamese: Nhà hát Thành phố Hồ Chí Minh), is an opera house in Ho Chi Minh City, Vietnam. It is an example of French Colonial architecture in Vietnam.",5));
        Collections.sort(list, new Comparator<Landmark>() {
            @Override
            public int compare(Landmark landmark, Landmark t1) {
                return (int) (t1.getRating() - landmark.getRating());
            }
        });
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.favourite_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.favourtie){
            Toast.makeText(this,"Favourite",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}