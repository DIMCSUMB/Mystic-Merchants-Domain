package com.example.mystic_merchants_domain;

import androidx.room.Database;
import androidx.room.RoomDatabase;

//Assisted by Android Room Walkthrough
@Database(entities = {Users.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{
    public abstract UsersDAO usersDAO();
}
