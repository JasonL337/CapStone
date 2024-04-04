/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventorytracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author panoff_894755
 */

// For viewing the item types and items. The InventoryGUI creates an amount of tables that are the amount of different itemTypes, retrieved from this class
// and the number of rows which are also gotten from this class are the number of items in that item type. This class is the handshake between the GUI and the 
// database retrieving.
// This class formats the data retrieved from SQL in a formatted string to be easily parsed into tables.
public class ViewInv extends Inventory {
    public void hitRefresh() {
        System.out.println(getChanges());
        resetChanges();
    }
    
    // Returns a HashMap of keys being each item type name + description as a string and then an arrayList of String arrays of the item data points.
    public HashMap<String, ArrayList<String[]>> returnData() {
        hitRefresh();
        HashMap<String, ArrayList<String[]>> data = new HashMap<>();
        
        // This would be equivalent to calling into the database and having the SQL return a String or json, but now it is just a String.
        String myItems = tempItems;
        String myTypes = tempTypes;
        String[] lines = myItems.split("\n"); // Split the string into lines
        String[] types = myTypes.split("\n");
        
        
        
        // Loops to go through the giant string of item types and data and the items and their data in the inventory class and put it in a Hashmap.
        for (String type : types) {
            String itemType = type.substring(type.indexOf(": ") + 2, type.lastIndexOf(": "));
            
            
            data.put(itemType, new ArrayList<>());
        
            for (String line : lines) {
                if (line.contains(itemType)) { // Check if the line contains the search term
                    String[] dataPoints = line.substring(line.indexOf(": ") + 2).split(": "); // Split the line into data points
                    data.get(itemType).add(dataPoints);
                }
            }
        }
        return data;
    }
    
    public void returnItemTypes() {
        
    }
    
}
