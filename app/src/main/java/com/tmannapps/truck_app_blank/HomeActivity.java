package com.tmannapps.truck_app_blank;

import static com.tmannapps.truck_app_blank.R.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    RecyclerView trucksRecyclerView;
    TrucksRecyclerViewAdapter trucksRecyclerViewAdapter;
    List<Truck> truckList = new ArrayList<>();

    ImageButton menuButton;

    String [] modelList = {"Holden Ute", "Ford Ute", "Honda Mover", "Isuzu Bucket", "MAC Tray", "Caterpillar Crane Loader"};
    String [] typeList = {"Utility vehicle", "Utility vehicle", "Moving truck", "Bucket truck", "Open tray truck", "Crane loader tray truck"};
    String [] capacityList = {"Small", "Small", "Medium", "Medium", "Large", "Large"};
    int [] imageList = {drawable.utesmall, drawable.utesmall2, drawable.movingtruckmed, drawable.buckettruckmed,
            drawable.baletrucklarge, drawable.cranetrucklarge};
    //images from https://www.freepik.com/free-photos-vectors/cartoon-truck


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        menuButton = findViewById(R.id.imageButtonMenu);
        trucksRecyclerView = findViewById(id.recyclerView);
        trucksRecyclerViewAdapter = new TrucksRecyclerViewAdapter(truckList, this);
        trucksRecyclerView.setAdapter(trucksRecyclerViewAdapter);
        trucksRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        for (int i = 0; i < modelList.length; i++) {
            Truck truck = new Truck(i, modelList[i], typeList[i], capacityList[i], imageList[i]);
            truckList.add(truck);
        }
    }


}