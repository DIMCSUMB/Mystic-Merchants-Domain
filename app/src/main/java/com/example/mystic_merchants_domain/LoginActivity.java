package com.example.mystic_merchants_domain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initialize EditTexts & Button
        editTextUsername = findViewById(R.id.login_edittext_Username);
        editTextPassword = findViewById(R.id.login_edittext_Password);
        buttonLogin = findViewById(R.id.login_button_Login);

        //Initialize Room database
        //Assisted by ChatGPT
        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,
                "merchants_database").allowMainThreadQueries().build();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                //Authenticate the user
                //Assisted by Android Room Walkthrough & ChatGPT
                Users user = database.usersDAO().getUser(username);
                if (user != null && user.getPassword().equals(password)) {
                    //Save login state and the current user's admin status
                    SharedPreferences.Editor editor = getSharedPreferences("shared_prefs", MODE_PRIVATE).edit();
                    editor.putBoolean("is_logged_in", true);
                    editor.putBoolean("is_admin", user.isAdmin);
                    editor.putString("username", username);
                    editor.apply();

                    //Redirect to the LandingPage
                    Intent intent = new Intent(LoginActivity.this, LandingPageActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    //Incorrect credentials
                    editTextPassword.setError("Invalid password");
                    Toast.makeText(LoginActivity.this, "Invalid credentials.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
