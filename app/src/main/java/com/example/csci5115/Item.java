package com.example.csci5115;

import java.io.Serializable;
import java.util.Date;

public class Item implements Serializable {
    private String itemName;
    private Date itemAddedDate;
    private String location;

    public Item(String itemName, Date itemAddedDate, String location) {
        this.itemName = itemName;
        this.itemAddedDate = itemAddedDate;
        this.location = location;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Date getItemAddedDate() {
        return itemAddedDate;
    }

    public void setItemAddedDate(Date itemAddedDate) {
        this.itemAddedDate = itemAddedDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
