package com.example.mystic_merchants_domain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.ListView;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ItemsActivity extends AppCompatActivity {

    private ListView itemListView;
    private ItemAdapter itemAdapter;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        itemListView = findViewById(R.id.item_ListView);

        //Initialize the Room database
        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,
                "merchants_database").build();

        setAdapter();
    }

    //Assisted by AI. Needed help implementing the ListView I created with the adapter
    //Some parts used from some of the other classes I created
    //Was informed that grabbing the items in a background thread assists with UI responsiveness
    private void setAdapter() {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            List<Item> items = database.itemDAO().getAllItems();

            runOnUiThread(() -> {
                itemAdapter = new ItemAdapter(ItemsActivity.this, items);
                itemListView.setAdapter(itemAdapter);
            });
        });
    }
}