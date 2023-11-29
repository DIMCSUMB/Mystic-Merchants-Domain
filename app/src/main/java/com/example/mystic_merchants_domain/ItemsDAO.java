package com.example.mystic_merchants_domain;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

//Assisted by UsersDAO file and ChatGPT
@Dao
public interface ItemsDAO {
    @Insert
    void insert (Items items);

    @Query("SELECT * FROM items_table")
    List<Items> getAllItems();
}
