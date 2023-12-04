package com.example.mystic_merchants_domain;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PouchDAO {
    @Insert
    void insert(Pouch pouch);

    @Query("SELECT * FROM Pouch WHERE itemId = :itemId")
    Pouch getPouchByItemID(int itemId);

    @Query("UPDATE pouch SET quantity = :newQuantity WHERE itemId = :itemId")
    void updateItemQuantity(int itemId, int newQuantity);

    @Update
    void updatePouch (Pouch pouch);

    @Delete
    void deletePouch (Pouch pouch);

    @Query("SELECT * FROM Pouch")
    List<Pouch> getAllPouchItems();
}
