package com.midterm.project.touristguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class LandmarkDetail extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private CircleIndicator3 circleIndicator3;
    private List<Photo> mListPhoto;
    private Landmark landmark;
    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int currentPosition = viewPager2.getCurrentItem();
            if(currentPosition == mListPhoto.size() -1 ){
                viewPager2.setCurrentItem(0);
            }
            else {
                viewPager2.setCurrentItem(currentPosition + 1);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landmark_detail);
        Bundle bundle = getIntent().getExtras();
        viewPager2 = findViewById(R.id.video_pager_2);
        circleIndicator3 = findViewById(R.id.circle_indicator3);
        TextView detailName = findViewById(R.id.detail_Name);
        TextView detailDes = findViewById(R.id.detail_description);
        TextView detailPhone = findViewById(R.id.detail_phone);
        TextView detailAddress = findViewById(R.id.detail_address);
        if(bundle == null){
            return;
        }
        landmark = (Landmark) bundle.get("object_landmark");
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);

        //HERE
        detailName.setText(landmark.getName());
        detailDes.setText(landmark.getDescription());
        detailPhone.setText("Phone Number: "+landmark.getPhoneNumber());
        detailAddress.setText("Address: "+ landmark.getAddress());
        mListPhoto = getListPhoto();
        PhotoAdapter photoAdapter = new PhotoAdapter(mListPhoto);
        viewPager2.setAdapter(photoAdapter);
        circleIndicator3.setViewPager(viewPager2);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable,3000);
            }
        });

    }

    private List<Photo> getListPhoto(){
        List<Photo> list = new ArrayList<>();
        for(int i=0;i<landmark.getResourceImage().length;i++)
        {
            list.add(new Photo(landmark.getResourceImage()[i]));
        }
        return list;
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable,3000);
    }
}