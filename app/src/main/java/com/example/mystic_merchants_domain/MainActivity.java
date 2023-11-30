package com.example.mystic_merchants_domain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonLogin;
    private Button buttonCreateAccount;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize buttons
        buttonLogin = findViewById(R.id.main_button_Login);
        buttonCreateAccount = findViewById(R.id.main_button_createAccount);

        //Initialize Room database
        //Assisted by Android Developer Website
            database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,
                    "merchants_database").allowMainThreadQueries().build();

        //Check if this is the first run to insert predefined users
        SharedPreferences sharedPreferences = getSharedPreferences("shared_pref", MODE_PRIVATE);
        if (sharedPreferences.getBoolean("first_run", true)) {
            insertPredefinedUsers();
        } else {
            sharedPreferences.edit().putBoolean("first_run", false).apply();
        }

        //Check if the user is already logged in
        //Assisted by ChatGPT & Android Developer Website
        SharedPreferences sharedPref = getSharedPreferences("shared_pref", MODE_PRIVATE);
        boolean isLoggedIn = sharedPref.getBoolean("is_logged_in", false);

        if (isLoggedIn) {
            //Redirect to LandingPage if the user is already logged in
            Intent intent = new Intent(MainActivity.this, LandingPageActivity.class);
            startActivity(intent);
            finish();
        }

        //Set onClickListener for Login button
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Redirect to LoginActivity
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        //Set onClickListener for Create Account button
        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Redirect to CreateAccountActivity
                Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });
    }

    private void insertPredefinedUsers() {
        Users user1 = new Users("testuser1", "testuser1", false);
        Users admin2 = new Users("admin2", "admin2", true);

        //Insert users into database
        database.usersDAO().insert(user1);
        database.usersDAO().insert(admin2);
    }
}