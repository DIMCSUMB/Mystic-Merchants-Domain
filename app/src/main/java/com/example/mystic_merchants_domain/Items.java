package com.example.mystic_merchants_domain;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "items_table")
public class Items {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String name;
    public String description;
    public int damage; //0 for items that don't have damage
    public String attributes;
    public int quantity;
    public int price;

    public Items(String name, String description, int damage,
                 String attributes, int quantity, int price) {
        this.name = name;
        this.description = description;
        this.damage = damage;
        this.attributes = attributes;
        this.quantity = quantity;
        this.price = price;
    }
}
