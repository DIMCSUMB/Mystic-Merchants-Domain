package com.example.mystic_merchants_domain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import java.util.List;

public class ItemsActivity extends AppCompatActivity {

    private AppDatabase database;
    private List<Items> itemsList;
    private RecyclerView recyclerView;
    private ItemsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,
                "merchants_database").build();
        itemsList = database.itemsDAO().getAllItems();

        recyclerView = findViewById(R.id.recycler_view);
        adapter = new ItemsAdapter(this, itemsList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}