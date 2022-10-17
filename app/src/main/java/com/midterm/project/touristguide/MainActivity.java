package com.midterm.project.touristguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcvLandmark;
    private LandmarkAdapter mLandmarkAdapter;
    private FloatingActionButton floatingActionButton;
    private List<Landmark> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcvLandmark = findViewById(R.id.rcv_landmark);
        floatingActionButton = findViewById(R.id.floatingButton);
        mLandmarkAdapter = new LandmarkAdapter(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        rcvLandmark.setLayoutManager(gridLayoutManager);

        mLandmarkAdapter.setData(getListLandmark());
        rcvLandmark.setAdapter(mLandmarkAdapter);
        rcvLandmark.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if(dy > 0){
                    floatingActionButton.hide();
                }
                else {
                    floatingActionButton.show();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }


    public List<Landmark> getListLandmark() {
        list = new ArrayList<>();
        list.add(new Landmark(new int[]{R.drawable.picture_1,R.drawable.dinhdoclap1,R.drawable.dinhdoclap2},"Indedenpence Palace","This is the place marking the complete victory of anti-American resistance war, libration of the South and national reunification.",5,"https://en.wikipedia.org/wiki/Independence_Palace","02838223652","135 Đ. Nam Kỳ Khởi Nghĩa, Phường Bến Thành, Quận 1, Thành phố Hồ Chí Minh"));
        list.add(new Landmark(new int[]{R.drawable.picture_2,R.drawable.ducba1,R.drawable.ducba2},"Notre-Dame Cathedral Basilica of Saigon","Established by French colonists who initially named it the Church of Saigon (French: l'Eglise de Saïgon), the cathedral was constructed between 1863 and 1880. The name Notre-Dame Cathedral has been used since 1959. It has two bell towers, reaching a height of 58 meters",3,"https://en.wikipedia.org/wiki/Notre-Dame_Cathedral_Basilica_of_Saigon","0914122229","01 Công xã Paris, Bến Nghé, Quận 1, Thành phố Hồ Chí Minh 70000"));
        list.add(new Landmark(new int[]{R.drawable.picture_3,R.drawable.benthanh1,R.drawable.benthanh2},"Bến Thành Market","Bến Thành Market (Vietnamese: Chợ Bến Thành) is located in the center of Hồ Chí Minh City, Vietnam in District 1. The market is one of the earliest surviving structures in Saigon and an important symbol of the city.",4,"https://en.wikipedia.org/wiki/B%E1%BA%BFn_Th%C3%A0nh_Market","0835210004","Lê Lợi, Phường Bến Thành, Quận 1, Thành phố Hồ Chí Minh\n"));
        list.add(new Landmark(new int[]{R.drawable.picture_4,R.drawable.nhahat1,R.drawable.nhahat2},"Municipal Theatre","The Municipal Theatre of Ho Chi Minh City, also known as Saigon Municipal Opera House (Vietnamese: Nhà hát Thành phố Hồ Chí Minh), is an opera house in Ho Chi Minh City, Vietnam. It is an example of French Colonial architecture in Vietnam.",5,"https://en.wikipedia.org/wiki/Municipal_Theatre,_Ho_Chi_Minh_City","0838237419","07 Công Trường Lam Sơn, Bến Nghé, Quận 1, Thành phố Hồ Chí Minh 700000"));
        Collections.sort(list, new Comparator<Landmark>() {
            @Override
            public int compare(Landmark landmark, Landmark t1) {
                return (int) (t1.getRating() - landmark.getRating());
            }
        });
        return list;
    }

    public void addItem(View view){
//        list.add(new Landmark(R.drawable.picture_1,"Indedenpence Palace2","This is the place marking the complete victory of anti-American resistance war, libration of the South and national reunification.",5));
//        LandmarkAdapter mLandmarkAdapter = new LandmarkAdapter(this);
//        mLandmarkAdapter.setData(list);
//        rcvLandmark.setAdapter(mLandmarkAdapter);
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