package com.example.kien.starbuzz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    //Create an OnItemClickListener
    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> listView, View itemView, int position, long id) {
            if (position == 0) {
                Intent intent = new Intent(MainActivity.this, DrinkCategoryActivity.class);
                startActivity(intent);
            }
        }
    };
    //Add the listener to the list view
    ListView listView = (ListView) findViewById(R.id.list_options);
            listView.setOnItemClickListener(itemClickListener);
    }

}
