package com.example.mystic_merchants_domain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddToPouchActivity extends AppCompatActivity {

    private ItemDAO itemDAO;
    private EditText editTextQuantity;
    private Button buttonAddToPouch;
    private AppDatabase database;
    private Item selectedItem;
    private int selectedItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_pouch);

        editTextQuantity = findViewById(R.id.pouch_edittext_Quantity);
        buttonAddToPouch = findViewById(R.id.pouch_button_Add);

        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "merchants_database").build();
        itemDAO = database.itemDAO();

        // Retrieve the selected item ID passed from ItemAdapter
        int selectedItemId = getIntent().getIntExtra("SELECTED_ITEM_ID", -1);
        // Get the selected item from the database
        new SelectedItem().execute(selectedItemId);

        buttonAddToPouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int requestedQuantity = Integer.parseInt(editTextQuantity.getText().toString());
                    int availableStock = selectedItem.getQuantity();

                    if (requestedQuantity <= 0 || requestedQuantity > availableStock) {
                        Toast.makeText(getApplicationContext(), "Invalid or excessive quantity!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Update the pouch and item stock (asynchronously)
                    new UpdatePouchAndStock().execute(requestedQuantity);
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Please enter a valid quantity", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class SelectedItem extends AsyncTask<Integer, Void, Item> {
        @Override
        protected Item doInBackground(Integer... integers) {
            return database.itemDAO().getItemById(integers[0]);
        }

        @Override
        protected void onPostExecute(Item item) {
            selectedItem = item;
            if (selectedItem != null) {
                TextView itemName = findViewById(R.id.pouch_textview_Name);
                itemName.setText(selectedItem.getName());
            }
        }
    }

    private class UpdatePouchAndStock extends AsyncTask<Integer, Void, Void> {
        @Override
        protected Void doInBackground(Integer... quantities) {
            int addQuantity = quantities[0];
            Pouch existingPouchItem = database.pouchDAO().getPouchByItemID(selectedItemId);

            if (existingPouchItem == null) {
                Pouch newPouchItem = new Pouch();
                newPouchItem.setItemId(selectedItemId);
                newPouchItem.setQuantity(addQuantity);
                database.pouchDAO().insert(newPouchItem);
            } else {
                existingPouchItem.setQuantity(existingPouchItem.getQuantity() + addQuantity);
                database.pouchDAO().updatePouch(existingPouchItem);
            }

            selectedItem.setQuantity(selectedItem.getQuantity() - addQuantity);
            database.itemDAO().updateItemQuantity(selectedItemId, selectedItem.getQuantity());

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(getApplicationContext(), "Item added to your pouch!", Toast.LENGTH_SHORT).show();
        }
    }
}