package com.example.csci5115;

import android.widget.CheckBox;

import java.io.Serializable;
import java.util.Date;

public class Item implements Serializable {
    private String itemName;
    private String itemAddedDate;
    private String location;
    private String image;
    private boolean checked;

    public Item(String itemName, String itemAddedDate, String location) {
        this.itemName = itemName;
        this.itemAddedDate = itemAddedDate;
        this.location = location;
        this.image = "no_image";
        this.checked = false;
    }

    public Item(String itemName, String itemAddedDate, String location, String image) {
        this.itemName = itemName;
        this.itemAddedDate = itemAddedDate;
        this.location = location;
        this.image = image;
        this.checked = false;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemAddedDate() {
        return itemAddedDate;
    }

    public void setItemAddedDate(String itemAddedDate) {
        this.itemAddedDate = itemAddedDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String newImage){
        image = newImage;
    }

    public void setChecked(boolean value){this.checked = value;}

    public boolean getChecked(){return this.checked;}
}
