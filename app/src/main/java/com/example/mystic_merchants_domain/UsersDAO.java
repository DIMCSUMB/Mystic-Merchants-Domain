package com.example.mystic_merchants_domain;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

//Assisted by Android Room Walkthrough
@Dao
public interface UsersDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Users users);

    @Query("SELECT * FROM users_table WHERE username = :username AND password = :password")
    Users getUser(String username, String password);
}
