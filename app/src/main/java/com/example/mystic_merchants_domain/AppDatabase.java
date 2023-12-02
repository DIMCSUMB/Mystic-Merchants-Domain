package com.example.mystic_merchants_domain;

import androidx.room.Database;
import androidx.room.RoomDatabase;

//Assisted by Android Room Walkthrough
@Database(entities = {Users.class, Item.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase{
    public abstract UsersDAO usersDAO();
    public abstract ItemDAO itemDAO();
}
