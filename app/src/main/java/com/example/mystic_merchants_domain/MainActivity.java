package com.example.mystic_merchants_domain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
        if (database == null) {
            database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,
                    "merchants_database").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }

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

        insertItemsIfNeeded(); //Calls the method to insert the default items if needed
    }

    private void insertPredefinedUsers() {
        Users user1 = new Users("testuser1", "testuser1", false);
        Users admin2 = new Users("admin2", "admin2", true);

        //Insert users into database
        database.usersDAO().insert(user1);
        database.usersDAO().insert(admin2);
    }

    private void insertItemsIfNeeded() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared_pref", MODE_PRIVATE);
        boolean itemsInserted = sharedPreferences.getBoolean("itemsInserted", false);

        if(!itemsInserted) {
            insertItem("Doom Bow", "No mortal can fire this dreaded bow without resting in between shots. " + "It requires tremendous skill to wield.",
                    550, "", 2, 800);
/*            insertItem("Wand of Retribution", "A golden wand of breathtaking power, created by elemental forces from the realms of ice and flame to unleash revenge on their enemies.",
                    172, "", 3, 950);
            insertItem("Staff of Extreme Prejudice", "Once the most powerful staff in existence; a crack in its frame has rendered it incapable of focusing fire. It remains uniquely deadly.",
                    875, "", 1, 600);
            insertItem("Leviathan Armor", "Magnificent leather armor made from the hide of the great leviathan, slain hundreds of years ago in a savage battle in the vast deeps.",
                    0, "+21 DEF, +7 DEX", 2, 950);
            insertItem("Gladiator Guard", "Although refurbished, this breastplate is the very same Oryx received as a gift from the royal family in celebration of his gladiatorial prowess.",
                    0,"+7 SPD, +5 ATT, +5 DEX, +20 DEF", 2, 1100 );
            insertItem("Ritual Robe", "A thin hood with a faded golden lace worn for sacrificial ceremonies. It has an aura of evil around it.",
                    0, "+12 DEF, +5 ATT, +20 WIS, +50 MP", 1,1100);
            insertItem("Omnipotence Ring", "An unfathomable amount of strength pulses through this ring. Only the most righteous mortals can touch this ring and live.",
                    0, "+80 HP, +80 MP, +4 ATT, +4 DEF, +4 SPD, +4 DEX, +4 VIT, +4 WIS",1, 5000);
            insertItem("Ring of Unbound Health", "An immaculately perfect topaz embedded in an exquisite gold ring.",
                    0, "+180 HP", 4, 500);
            insertItem("Seal of the Blasphemous Prayer", "A seal that is said to briefly borrow the power of the Gods themselves. Some Paladins shun it's dark power, others embrace it.",
                    0, "Invulnerable for 3 Seconds", 1, 1500);
            insertItem("Orb of Conflict", "This orb of ancient bloodstone has a dark reputation of twisting those who wield it.",
                    0, "+5 ATT, +5 DEX, Speedy & Damaging for 3 Seconds", 1, 1200);
            insertItem("Quiver of Thunder", "This strange quiver was created in a forgotten age by a lost tribe of Dark Elves.",
                    0, "+5 ATT, Enemy dazed for 3 seconds", 1, 1000);
            insertItem("Creators Ring", "A ring worn by powerful creators in the goal to observe their brand new creation.",
                    0, "+99,999 HP, +99,999 VIT, +5,000 ATT", 1, 99999);
                    */

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("itemsInserted", true);
            editor.apply();
        }
    }

    private void insertItem(String name, String description, int damage, String attributes, int quantity, int price) {
        Items items = new Items(name, description, damage, attributes, quantity, price);
        database.itemsDAO().insert(items);
    }
}