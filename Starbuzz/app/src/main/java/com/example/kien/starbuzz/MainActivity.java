package com.example.kien.starbuzz;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {

    private SQLiteDatabase db;
    private Cursor favoritesCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupOptionsListView();
        setupFavoritesListView();
    }

    private void setupOptionsListView(){
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

        private void setupFavoritesListView() {
            //populate the list_favorites ListView from a cursor
            ListView listFavorites = (ListView) findViewById(R.id.list_favourites);
            try {
                SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
                db = starbuzzDatabaseHelper.getReadableDatabase();
                favoritesCursor = db.query("DRINK", new String[] {"_id","NAME"}, "FAVORITE = 1", null, null, null, null);

                CursorAdapter favoriteAdapter = new SimpleCursorAdapter(MainActivity.this, android.R.layout.simple_list_item_1, favoritesCursor, new String[] {"NAME"}, new int[]{android.R.id.text1}, 0);

                listFavorites.setAdapter(favoriteAdapter);
            } catch (SQLiteException e) {
                Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
                toast.show();

            }

            //Navigate to the DrinkActivty if a drink is clicked
            listFavorites.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> listView, View v, int position, long id) {
                    Intent intent = new Intent(MainActivity.this, DrinkActivity.class);
                    intent.putExtra(DrinkActivity.EXTRA_DRINKID, (int)id);
                    startActivity(intent);
                }
            });

        }

        //Close the cursor and the database in the onDestroy() method
        @Override
        public void onDestroy(){
            super.onDestroy();
            favoritesCursor.close();
            db.close();
        }

    }


