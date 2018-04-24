package com.example.aras.slapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ListView listView;
    String saveVariable;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        listView =(ListView) findViewById(R.id.lista);

        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("StationNamn", listView.getItemAtPosition(i).toString());
                startActivity(intent);
            }
        });
        listView.setAdapter(mAdapter);



    }

    void OnClick(View view) {

        EditText editText = (EditText) findViewById(R.id.searchBar);
        String apiCaller = "http://api.sl.se/api2/typeahead.json?key=cde87755aaf24939b65686274a8274db&searchstring=" + editText.getText().toString() + "&stationsonly=true";
        if (editText.getText().toString().isEmpty()) {

        }
        else {
            new ApiCaller(this, apiCaller).execute(apiCaller);
        }
    }

    void setupList(String[] recieveNames,int[] stationId){



        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, recieveNames);
        ListView listAdapter = (ListView) findViewById(R.id.lista);
        listAdapter.setAdapter(adapter);


    }






}




