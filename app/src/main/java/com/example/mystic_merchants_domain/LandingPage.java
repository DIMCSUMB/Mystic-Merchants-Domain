package com.example.mystic_merchants_domain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LandingPage extends AppCompatActivity {

    private TextView textViewWelcome;
    private Button buttonLogout;
    private Button buttonAdminSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        textViewWelcome = findViewById(R.id.landing_textview_Welcome);
        buttonLogout = findViewById(R.id.landing_button_Logout);
        buttonAdminSettings = findViewById(R.id.landing_button_Admin);

        //Syntax assisted by ChatGPT
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "User");
        boolean isAdmin = sharedPreferences.getBoolean("is_admin", false);

        //Welcome message with current username
        TextView textViewWelcome = findViewById(R.id.landing_textview_Welcome);
        textViewWelcome.setText("Welcome " + username + "!");

        //Make admin settings button visible if current user is an admin
        if (isAdmin) {
            buttonAdminSettings.setVisibility(View.VISIBLE);
        } else {
            buttonAdminSettings.setVisibility(View.INVISIBLE);
        }

        //Set onClickListener for Logout button
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Clear shared preferences
                //Assisted by Android Development Website
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                //Redirect back to MainActivity
                Intent intent = new Intent(LandingPage.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}