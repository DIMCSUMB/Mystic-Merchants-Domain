package com.example.mystic_merchants_domain;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ItemDAO {

    @Insert
    void insert(Item item);

    @Delete
    void delete(Item item);

    @Query("SELECT * FROM item")
    List<Item> getAllItems();

    @Query("UPDATE item SET quantity = :quantity WHERE id = :itemId")
    void updateItemQuantity(int itemId, int quantity);

    @Query("SELECT * FROM item WHERE id = :id")
    Item getItemById(int id);
}
