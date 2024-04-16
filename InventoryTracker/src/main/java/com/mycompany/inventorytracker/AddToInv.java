/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventorytracker;

import java.awt.image.BufferedImage;

/**
 *
 * @author panoff_894755
 */
public class AddToInv extends Inventory {
    
    public String addItem(String itemName, int quantity, double price, String itemDescription, int shelfLife, String color, BufferedImage pfp, String location, String itemType) {
        // adding local changes
        addLocalChanges("added item with properties: " + itemName + ": " + quantity + ": " + price + ": " + itemDescription + ": " + shelfLife + ": " + color + ": " + "" + ": " + location + ": " + itemType + "\n");
        // printing local changes
        printLocalChanges();
        // adding ovrall changes
        addChanges(getLocalChanges());
        
        // adding a temp item
        addItemStringTemp(getLocalChanges());
        
        resetLocalChanges();
        return "successfully added Item";
    }
    
    public String addItemType(String name, String description) {
        // adding local changes
        addLocalChanges("added item type with properties: " + name + ": " + description + "\n");
        printLocalChanges();
        
        addTypeStringTemp(getLocalChanges());
        resetLocalChanges();
        return "successfully added Item Type";
    }
    
}
