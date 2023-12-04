package com.example.mystic_merchants_domain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddItemActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextDescription;
    private EditText editTextAttributes;
    private EditText editTextQuantity;
    private EditText editTextPrice;
    private Button addItemButton;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        //Initialize EditTexts and Button
        editTextName = findViewById(R.id.add_edittext_Name);
        editTextDescription = findViewById(R.id.add_edittext_Description);
        editTextAttributes = findViewById(R.id.add_edittext_Attributes);
        editTextQuantity = findViewById(R.id.add_edittext_Quantity);
        editTextPrice = findViewById(R.id.add_edittext_Price);
        addItemButton = findViewById(R.id.add_button_AddItem);

        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,
                "merchants_database").build();

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Collect data from the users input
                final String name = editTextName.getText().toString();
                final String description = editTextDescription.getText().toString();
                final String attributes = editTextAttributes.getText().toString();
                final int quantity = Integer.parseInt(editTextQuantity.getText().toString());
                final int price = Integer.parseInt(editTextPrice.getText().toString());

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //Create item object and set different properties
                        Item item = new Item();
                        item.setName(name);
                        item.setDescription(description);
                        item.setAttributes(attributes);
                        item.setQuantity(quantity);
                        item.setPrice(price);

                        //Get ItemDAO from database
                        ItemDAO itemDAO = database.itemDAO();

                        //Insert new item into database
                        itemDAO.insert(item);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //Let user know item was added successfully
                                Toast.makeText(AddItemActivity.this, "Item Added Successfully", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).start();
            }
        });
    }
}