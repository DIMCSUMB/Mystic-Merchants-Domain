package com.example.mystic_merchants_domain;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//Assisted by Android Room Walkthrough
@Entity(tableName = "users_table")
public class Users {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String username;
    public String password;
    public boolean isAdmin;

    //Generated Constructor
    public Users(@NonNull String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }
}
