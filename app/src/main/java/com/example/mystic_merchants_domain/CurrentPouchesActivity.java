package com.example.mystic_merchants_domain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.List;

public class CurrentPouchesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PouchAdapter pouchAdapter;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_pouches);

        recyclerView = findViewById(R.id.pouch_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "merchants_database").build();

        loadPouchItems();
    }

    private void loadPouchItems() {
        new Thread(() -> {
            List<Pouch> pouchItems = database.pouchDAO().getAllPouchItems();
            runOnUiThread(() -> {
                pouchAdapter = new PouchAdapter(pouchItems);
                recyclerView.setAdapter(pouchAdapter);
            });
        }).start();
    }
}