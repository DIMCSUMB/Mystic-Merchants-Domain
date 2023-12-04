package com.example.mystic_merchants_domain;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Pouch {
    @PrimaryKey(autoGenerate = true)
    public int pouchItemId;

    public int itemId; // Refers to the Item's ID

    public int quantity; // Quantity of this item in the pouch

    // Getters and setters
    public int getPouchItemId() {
        return pouchItemId;
    }

    public void setPouchItemId(int pouchItemId) {
        this.pouchItemId = pouchItemId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
