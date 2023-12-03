package com.example.mystic_merchants_domain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ItemsActivity extends AppCompatActivity {

    private ListView itemListView;
    private ItemAdapter itemAdapter;
    private AppDatabase database;
    private SearchView itemSearchView;
    private List<Item> allItems;
    private List<Item> filteredItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        //Initialize Views
        itemListView = findViewById(R.id.item_ListView);
        itemSearchView = findViewById(R.id.item_SearchView);

        //Initialize the Room database
        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,
                "merchants_database").build();

        setAdapter();

        itemSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filteredItems.clear();
                for (Item item : allItems) {
                    if (item.getName().toLowerCase().contains(newText.toLowerCase())) {
                        filteredItems.add(item);
                    }
                }
                runOnUiThread(() -> itemAdapter.notifyDataSetChanged());
                return true;
            }
        });
    }

    //Assisted by AI. Needed help implementing the ListView I created with the adapter
    //Some parts used from some of the other classes I created
    //Was informed that grabbing the items in a background thread assists with UI responsiveness
    private void setAdapter() {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                allItems = database.itemDAO().getAllItems();
                runOnUiThread(() -> {
                    filteredItems = new ArrayList<>(allItems);
                    itemAdapter = new ItemAdapter(ItemsActivity.this, filteredItems);
                    itemListView.setAdapter(itemAdapter);
                });
            } catch (Exception e) {
                Log.e("ItemsActivity", "Error getting items from database", e);
            }
        });
    }
}