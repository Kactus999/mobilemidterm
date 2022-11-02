package com.midterm.project.touristguide;

import androidx.annotation.AnyRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private RecyclerView rcvLandmark;
    private LandmarkAdapter mLandmarkAdapter;
    private FloatingActionButton floatingActionButton;
    private List<Landmark> list;
    private RecyclerView rcvAddItem;
    private AddItemLandMarkAdapter addItemLandMarkAdapter;
    ArrayList<Uri> uriArrayList = new ArrayList<>();
    private static final int REAR_PERMISSION = 101;
    private int rating;
    private boolean isClicked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcvLandmark = findViewById(R.id.rcv_landmark);
        floatingActionButton = findViewById(R.id.floatingButton);
        mLandmarkAdapter = new LandmarkAdapter(this);

        LinearLayoutManager LinearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcvLandmark.setLayoutManager(LinearLayoutManager);

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
        list.add(new Landmark(new Uri[]{getUriToDrawable(MainActivity.this,R.drawable.doclap3),(getUriToDrawable(MainActivity.this,R.drawable.dinhdoclap1)),(getUriToDrawable(MainActivity.this,R.drawable.dinhdoclap2)),(getUriToDrawable(MainActivity.this,R.drawable.picture_1))},"Indedenpence Palace","This is the place marking the complete victory of anti-American resistance war, libration of the South and national reunification.",5,"https://en.wikipedia.org/wiki/Independence_Palace","02838223652","135 Đ. Nam Kỳ Khởi Nghĩa, Phường Bến Thành, Quận 1, Thành phố Hồ Chí Minh",false));
        list.add(new Landmark(new Uri[]{getUriToDrawable(MainActivity.this,R.drawable.ducba3),getUriToDrawable(MainActivity.this,R.drawable.ducba1),getUriToDrawable(MainActivity.this,R.drawable.ducba2),(getUriToDrawable(MainActivity.this,R.drawable.picture_2))},"Notre-Dame Cathedral\n Basilica of Saigon","Established by French colonists who initially named it the Church of Saigon, the cathedral was constructed between 1863 and 1880.",3,"https://en.wikipedia.org/wiki/Notre-Dame_Cathedral_Basilica_of_Saigon","0914122229","01 Công xã Paris, Bến Nghé, Quận 1, Thành phố Hồ Chí Minh 70000",false));
        list.add(new Landmark(new Uri[]{getUriToDrawable(MainActivity.this,R.drawable.benthanh3),getUriToDrawable(MainActivity.this,R.drawable.benthanh1),getUriToDrawable(MainActivity.this,R.drawable.benthanh2),(getUriToDrawable(MainActivity.this,R.drawable.picture_3))},"Bến Thành Market","Bến Thành Market is located in the center of Hồ Chí Minh City. The market is one of the earliest surviving structures in Saigon and an important symbol of the city.",4,"https://en.wikipedia.org/wiki/B%E1%BA%BFn_Th%C3%A0nh_Market","0835210004","Lê Lợi, Phường Bến Thành, Quận 1, Thành phố Hồ Chí Minh\n",false));
        list.add(new Landmark(new Uri[]{getUriToDrawable(MainActivity.this,R.drawable.nhahat3),getUriToDrawable(MainActivity.this,R.drawable.nhahat1),getUriToDrawable(MainActivity.this,R.drawable.nhahat2),(getUriToDrawable(MainActivity.this,R.drawable.picture_4))},"Municipal Theatre","The Municipal Theatre of Ho Chi Minh City, is an opera house in Ho Chi Minh City, Vietnam. It is an example of French Colonial architecture in Vietnam.",5,"https://en.wikipedia.org/wiki/Municipal_Theatre,_Ho_Chi_Minh_City","0838237419","07 Công Trường Lam Sơn, Bến Nghé, Quận 1, Thành phố Hồ Chí Minh 700000",false));

        Collections.sort(list, new Comparator<Landmark>() {
            @Override
            public int compare(Landmark landmark, Landmark t1) {
                return (int) (t1.getRating() - landmark.getRating());
            }
        });
        return list;
    }

    public void addItem(View view){
        openAddDialog();
    }
    private void openAddDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_scrollable_add);
        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttribute = window.getAttributes();
        window.setAttributes(windowAttribute);

        dialog.setCancelable(true);

        //Add edit text here
        rcvAddItem = dialog.findViewById(R.id.rcv_Gallery);
        Button btnCancel = dialog.findViewById(R.id.btn_cancel_add_landmark);
        Button btnaddLandmark = dialog.findViewById(R.id.btn_add_landmark);
        Button btnChoose = dialog.findViewById(R.id.choose_picture);
        EditText addName = dialog.findViewById(R.id.add_name);
        EditText addDescription = dialog.findViewById(R.id.add_description);
        EditText addPhone = dialog.findViewById(R.id.add_phone);
        EditText addWiki = dialog.findViewById(R.id.add_wiki);
        EditText addAddress = dialog.findViewById(R.id.add_address);

        Spinner spinner = dialog.findViewById(R.id.rating_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(MainActivity.this,R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(MainActivity.this);
        addItemLandMarkAdapter = new AddItemLandMarkAdapter(uriArrayList);
        rcvAddItem.setLayoutManager(new GridLayoutManager(MainActivity.this,4));
        rcvAddItem.setAdapter(addItemLandMarkAdapter);
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REAR_PERMISSION);
        }

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"),1);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                uriArrayList.clear();
            }
        });
        btnaddLandmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri[] uriImages = new Uri[uriArrayList.size()];
                for(int i=0;i<uriArrayList.size();i++){
                    uriImages[i] = uriArrayList.get(i);
                }
                list.add(new Landmark(uriImages,addName.getText().toString(),addDescription.getText().toString(),rating,addWiki.getText().toString(),addPhone.getText().toString(),addAddress.getText().toString(),false));
                LandmarkAdapter mLandmarkAdapter = new LandmarkAdapter(MainActivity.this);
                Collections.sort(list, new Comparator<Landmark>() {
                    @Override
                    public int compare(Landmark landmark, Landmark t1) {
                        return (int) (t1.getRating() - landmark.getRating());
                    }
                });
                mLandmarkAdapter.setData(list);
                rcvLandmark.setAdapter(mLandmarkAdapter);
                Toast.makeText(MainActivity.this,"Added",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                uriArrayList.clear();
            }
        });
        dialog.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.favourite_menu,menu);

        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        ArrayList<Landmark> subList = new ArrayList<>();
        if(item.getItemId() == R.id.favbtn){
            if(!isClicked){
                for(int i=0;i<list.size();i++){
                    if(list.get(i).isFavourite()){
                        subList.add(new Landmark(list.get(i).getResourceImage(),list.get(i).getName(),list.get(i).getDescription(),list.get(i).getRating(),list.get(i).getWikipage(),list.get(i).getPhoneNumber(),list.get(i).getAddress(),list.get(i).isFavourite()));
                    }
                }
                isClicked = true;
                mLandmarkAdapter.setData(subList);
                rcvLandmark.setAdapter(mLandmarkAdapter);
            }
            else{
                mLandmarkAdapter.setData(list);
                rcvLandmark.setAdapter(mLandmarkAdapter);
                isClicked = false;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            if(data.getClipData() != null){
                int x = data.getClipData().getItemCount();
                for(int i=0 ; i<x;i++){
                    uriArrayList.add(data.getClipData().getItemAt(i).getUri());
                }
                addItemLandMarkAdapter.notifyDataSetChanged();
            }
            else if(data.getData() != null){
                String imageUrl = data.getData().getPath();
                uriArrayList.add(Uri.parse(imageUrl));
            }
        }
    }
    public final Uri getUriToDrawable(@NonNull Context context,
                                             @AnyRes int drawableId) {
        Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                + "://" + context.getResources().getResourcePackageName(drawableId)
                + '/' + context.getResources().getResourceTypeName(drawableId)
                + '/' + context.getResources().getResourceEntryName(drawableId) );
        return imageUri;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String ratingTemp =  adapterView.getItemAtPosition(i).toString();
        rating = Integer.parseInt(ratingTemp);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}