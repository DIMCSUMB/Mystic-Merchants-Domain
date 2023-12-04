package com.example.mystic_merchants_domain;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

//Assisted by Android Room Walkthrough
@Dao
public interface UsersDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Users users);

    @Query("SELECT * FROM users_table WHERE username = :username")
    Users getUser(String username);

    @Query("SELECT * FROM users_table")
    List<Users> getAllUsers();

    @Query("DELETE FROM users_table WHERE username = :username")
    void deleteUser (String username);
}
