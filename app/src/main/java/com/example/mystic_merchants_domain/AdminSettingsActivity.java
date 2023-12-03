package com.example.mystic_merchants_domain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminSettingsActivity extends AppCompatActivity {

    private Button buttonAdminAddItems;
    private Button buttonAdminDeleteItems;
    private Button buttonAdminDeleteUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_settings);

        //Initialization
        buttonAdminAddItems = findViewById(R.id.admin_button_addItems);
        buttonAdminDeleteItems = findViewById(R.id.admin_button_deleteItems);
        buttonAdminDeleteUser = findViewById(R.id.admin_button_deleteUser);

        buttonAdminAddItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminSettingsActivity.this, AddItemActivity.class);
                startActivity(intent);
            }
        });

        buttonAdminDeleteItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminSettingsActivity.this, DeleteItemActivity.class);
                startActivity(intent);
            }
        });
    }
}