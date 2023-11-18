package com.example.mystic_merchants_domain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonLogin;
    private Button buttonCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize buttons
        buttonLogin = findViewById(R.id.main_button_Login);
        buttonCreateAccount = findViewById(R.id.main_button_createAccount);

        //Check if the user is already logged in
        //Assisted by ChatGPT & Android Developer Website
        SharedPreferences sharedPref = getSharedPreferences("shared_pref", MODE_PRIVATE);
        boolean isLoggedIn = sharedPref.getBoolean("is_logged_in", false);

        if (isLoggedIn) {
            //Redirect to LandingPage if the user is already logged in
            Intent intent = new Intent(MainActivity.this, LandingPage.class);
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
}