package com.example.democontentb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readDatafromAppDemoA();
            }
        });

    }

    public void readDatafromAppDemoA() {
        ContentResolver contentResolver = getContentResolver();
        ReadData readData = new ReadData();
        readData.getAssetFiledataFromA(contentResolver);
    }
}