package com.example.mystic_merchants_domain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;
import androidx.room.Room;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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

        //Check to see if first run to reduce items being added more than once
        if (sharedPreferences.getBoolean("first_run", true)) {
            insertItems();
            sharedPreferences.edit().putBoolean("first_run", false).apply();
        }
    }

    private void insertItems() {
        Item item1 = new Item();
        item1.setImage("doom_bow.png");
        item1.setName("Doom Bow");
        item1.setDescription("No mortal can fire this dreaded bow without resting in between shots. It requires tremendous skill to wield.");
        item1.setAttributes("550 DMG");
        item1.setQuantity(2);
        item1.setPrice(800);

        Item item2 = new Item();
        item2.setImage("wand_of_retribution.png");
        item2.setName("Wand of Retribution");
        item2.setDescription("A golden wand of breathtaking power, created by elemental forces from the realms of ice and flame to unleash revenge on their enemies.");
        item2.setAttributes("172 DMG");
        item2.setQuantity(3);
        item2.setPrice(950);

        Item item3 = new Item();
        item3.setImage("staff_of_extreme_prejudice.png");
        item3.setName("Staff of Extreme Prejudice");
        item3.setDescription("Once the most powerful staff in existence; a crack in its frame has rendered it incapable of focusing fire. It remains uniquely deadly.");
        item3.setAttributes("875 DMG");
        item3.setQuantity(1);
        item3.setPrice(600);

        Item item4 = new Item();
        item4.setImage("leviathan_armor.png");
        item4.setName("Leviathan Armor");
        item4.setDescription("Magnificent leather armor made from the hide of the great leviathan, slain hundreds of years ago in a savage battle in the vast deeps.");
        item4.setAttributes("+21 DEF, +7 DEX");
        item4.setQuantity(2);
        item4.setPrice(950);

        Item item5 = new Item();
        item5.setImage("gladiator_guard.png");
        item5.setName("Gladiator Guard");
        item5.setDescription("Although refurbished, this breastplate is the very same Oryx received as a gift from the royal family in celebration of his gladiatorial prowess.");
        item5.setAttributes("+7 SPD, +5 ATT, +5 DEX, +20 DEF");
        item5.setQuantity(2);
        item5.setPrice(1100);

        Item item6 = new Item();
        item6.setImage("ritual_robe.png");
        item6.setName("Ritual Robe");
        item6.setDescription("A thin hood with a faded golden lace worn for sacrificial ceremonies. It has an aura of evil around it.");
        item6.setAttributes("+12 DEF, +5 ATT, +20 WIS, +50 MP");
        item6.setQuantity(1);
        item6.setPrice(1100);

        Item item7 = new Item();
        item7.setImage("omnipotence_ring.png");
        item7.setName("Omnipotence Ring");
        item7.setDescription("An unfathomable amount of strength pulses through this ring. Only the most righteous mortals can touch this ring and live.");
        item7.setAttributes("+80 HP, +80 MP, +4 ATT, +4 DEF, +4 SPD, +4 DEX, +4 VIT, +4 WIS");
        item7.setQuantity(1);
        item7.setPrice(5000);

        Item item8 = new Item();
        item8.setImage("ring_of_unbound_health.png");
        item8.setName("Ring of Unbound Health");
        item8.setDescription("An immaculately perfect topaz embedded in an exquisite gold ring.");
        item8.setAttributes("+180 HP");
        item8.setQuantity(4);
        item8.setPrice(500);

        Item item9 = new Item();
        item9.setImage("seal_of_the_blasphemous_prayer.png");
        item9.setName("Seal of the Blasphemous Prayer");
        item9.setDescription("A seal that is said to briefly borrow the power of the Gods themselves. Some Paladins shun it's dark power, others embrace it.");
        item9.setAttributes("Invulnerable for 3 seconds");
        item9.setQuantity(1);
        item9.setPrice(1500);

        Item item10 = new Item();
        item10.setImage("orb_of_conflict.png");
        item10.setName("Orb of Conflict");
        item10.setDescription("This orb of ancient bloodstone has a dark reputation of twisting those who wield it.");
        item10.setAttributes("+5 ATT, +5 DEX, Speedy and Damaging for 3 Seconds");
        item10.setQuantity(1);
        item10.setPrice(1200);

        Item item11 = new Item();
        item11.setImage("quiver_of_thunder.png");
        item11.setName("Quiver of Thunder");
        item11.setDescription("This strange quiver was created in a forgotten age by a lost tribe of Dark Elves.");
        item11.setAttributes("+5 ATT, Enemy dazed for 3 seconds");
        item11.setQuantity(1);
        item11.setPrice(1000);

        Item item12 = new Item();
        item12.setImage("creators_ring.png");
        item12.setName("Creators Ring");
        item12.setDescription("A ring worn by powerful creators in the goal to observe their brand new creation");
        item12.setAttributes("+99,999 HP, +99,999 VIT, +5,000 ATT");
        item12.setQuantity(1);
        item12.setPrice(99999);

        //Was assisted by AI generation with this part by asking how to add my items in my database
        //since the last implementation I tried was giving me complications.

        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
                @Override
                public void run() {
            database.itemDAO().insert(item1);
            database.itemDAO().insert(item2);
            database.itemDAO().insert(item3);
            database.itemDAO().insert(item4);
            database.itemDAO().insert(item5);
            database.itemDAO().insert(item6);
            database.itemDAO().insert(item7);
            database.itemDAO().insert(item8);
            database.itemDAO().insert(item9);
            database.itemDAO().insert(item10);
            database.itemDAO().insert(item11);
            database.itemDAO().insert(item12);
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