package com.example.mystic_merchants_domain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


//ChatGPT assisted with the troubleshooting and debugging of this activity.
//When I would start this activity it would automatically go back to the LandingPage
//Was told that it was accessing the database from the main thread but Room doesn't allow that
//It informed me about AsyncTask to move database operations to a background thread instead
//Successfully resolved the issue I was having :)
public class DeleteItemActivity extends AppCompatActivity {
    private ListView deleteListView;
    private List<Item> itemList;
    private ItemAdapter itemAdapter;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_item);

        deleteListView = findViewById(R.id.delete_ListView);
        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "merchants_database").build();
        new GetItemsTask().execute();
    }

    private class GetItemsTask extends AsyncTask<Void, Void, List<Item>> {
        @Override
        protected List<Item> doInBackground(Void... voids) {
            return database.itemDAO().getAllItems();
        }

        @Override
        protected void onPostExecute(List<Item> items) {
            itemList = items;
            itemAdapter = new ItemAdapter(DeleteItemActivity.this, itemList);
            deleteListView.setAdapter(itemAdapter);
            setupListViewListener();
        }
    }

    private void setupListViewListener() {
        deleteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                confirmDeletion(itemList.get(position));
            }
        });
    }

    private void confirmDeletion(final Item item) {
        new AlertDialog.Builder(this)
                .setTitle("Confirm Deletion")
                .setMessage("Are you sure you want to delete this item?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        new DeleteItemTask().execute(item);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private class DeleteItemTask extends AsyncTask<Item, Void, Void> {
        @Override
        protected Void doInBackground(Item... items) {
            database.itemDAO().delete(items[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(DeleteItemActivity.this, "Item deleted successfully", Toast.LENGTH_SHORT).show();
            new GetItemsTask().execute(); // Refresh the list
        }
    }
}
