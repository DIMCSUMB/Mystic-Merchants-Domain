package com.example.mystic_merchants_domain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonCreateAccount;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        //Initialization of database, editTexts, and Button
        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,
                "merchants_database").allowMainThreadQueries().build();
        editTextUsername = findViewById(R.id.create_edittext_Username);
        editTextPassword = findViewById(R.id.create_edittext_Password);
        buttonCreateAccount = findViewById(R.id.create_button_CreateAccount);

        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                //Assisted by ChatGPT (No special characters allowed)
                String characters = "^[a-zA-Z0-9]+$";

                //Validate Inputs
                if (username.isEmpty() || password.isEmpty()) {
                    //Username or Password empty
                    Toast.makeText(CreateAccountActivity.this, "Missing Username or Password",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                //Check if username already exists in database
                Users existingUser = database.usersDAO().getUser(username);
                if (existingUser != null) {
                    //Username already exists in database
                    editTextUsername.setError("Please enter a different username");
                    Toast.makeText(CreateAccountActivity.this, "Username already exists",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                //Check for special characters
                if (!username.matches(characters) || !password.matches(characters)) {
                    Toast.makeText(CreateAccountActivity.this, "No special characters allowed",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // Insert new user
                Users newUser = new Users(username, password, false);
                database.usersDAO().insert(newUser);

                //Navigate to LandingPage
                Intent intent = new Intent(CreateAccountActivity.this, LandingPageActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}