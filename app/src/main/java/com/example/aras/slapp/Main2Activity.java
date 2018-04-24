package com.example.aras.slapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    String stationNames;
    TextView name;
    int stationId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        stationNames = intent.getStringExtra("StationNamn");
        name = (TextView) (findViewById(R.id.textView));
        name.setText(stationNames);

    }

    void OnClick(View view) {


        String realTimeApiCaller = "http://api.sl.se/api2/typeahead.json?key=19adf1dce4f0461aaf1710499d6cfc6e&searchstring=";


    }

    public void startButtonOnClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}