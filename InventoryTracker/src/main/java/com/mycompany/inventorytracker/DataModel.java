/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventorytracker;

import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 *
 * @author panoff_894755
 */

// The way the class structure works is this:
// For adding item type or item, the InventoryGUI class has text fields where the user can add data about a new item type or item and it calls to this class
// with a request to add the Item type or item. If it can successfully add (no problems), it calls to the ItemMod class and that class adds it to the database with SQL actions.

public class DataModel {
    
    // This will be an instance variable so it is different for every person who makes changes. 
    String localChanges;
    
    public DataModel() {
        
    }
    
    
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

/*
    
    public void addItem(Item item, String itemType) {
        String name = item.getItemName();
        if (!items.containsKey(name))
        {
            item.addNewItemToDatabase();
            ItemType thisItemType = itemTypes.get(itemType);
            item.setName(thisItemType.getName());
            items.put(name, item);
        }
        else
            System.out.println(name + " already exists as an item!");
    }
    
    public void removeItem(String item) {
        items.remove(item);
    }
    
    public void addItemType(ItemType itemType) {
        String name = itemType.getName();
        if (!items.containsKey(name))
        {
            itemType.addNewItemTypeToDatabase();
            itemTypes.put(name, itemType);
        }
        else
            System.out.println(name + " already exists as an item!");
    }
    
    public void removeItemType(String itemType) {
        itemTypes.remove(itemType);
    }
    
    public void changeItem(String itemName, changedVals cv, int val) {
        if (cv == changedVals.quantity) {
            items.get(itemName).setQuantity(val);
        }
        if (cv == changedVals.daysLeftInStock) {
            items.get(itemName).setDaysLeftInStock(val);
        }
    }
    
    public void changeItem(String itemName, changedVals cv, String val) {
        
    }
    
    public void changeItem(String itemName, changedVals cv, BufferedImage val) {
        
    }
    
    public void changeItemType(String itemTypeName, changedVals cv, String val) {
        if (cv == changedVals.itemTypeName) {
            String ogName = itemTypes.get(itemTypeName).getName();
            itemTypes.get(itemTypeName).setName(val);
            for (String key : items.keySet()) {
                if (items.get(key).getName().equals(ogName))
                {
                    items.get(key).setName(ogName);
                }
            }
        }
        if (cv == changedVals.itemTypeDescription) {
            itemTypes.get(itemTypeName).setDescription(val);
        }
    }
    */
    
}
