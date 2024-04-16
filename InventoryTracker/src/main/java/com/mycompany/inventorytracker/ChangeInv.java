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
// For changing, it works similar to adding. However, it passes in the name of the item type or item you're changing and then goes from there. 
public class ChangeInv extends Inventory {
        
    public enum changedVals {
        quantity,
        itemName,
        price,
        shelfLife,
        itemDescription,
        color,
        picture,
        location,
        daysLeftInStock,
    }
    
    public enum changedTypeVals {
        name,
        description
    }
    // All of these handle cases as well
    public String changeItem(String name, changedVals cv, double change, boolean set) {
        // Depending on the changed value and the name, call the right method in the ItemMod class to make the SQL call.
        return "successfully changed Item";
    }
    
    public String changeItem(String name, changedVals cv, String change, boolean set) {
        // Depending on the changed value and the name, call the right method in the ItemMod class to make the SQL call.
        return "successfully changed Item";
    }
    
    public String changeItem(String name, changedVals cv, int change, boolean set) {
        // Depending on the changed value and the name, call the right method in the ItemMod class to make the SQL call.
        return "successfully changed Item";
    }
    
    public String changeItem(String name, changedVals cv, BufferedImage change) {
        // Depending on the changed value and the name, call the right method in the ItemMod class to make the SQL call.
        return "successfully changed Item";
    }
    
    public String changeItemType(String name, changedVals cv, String change, boolean set) {
        // Depending on the changed value and the name, call the right method in the ItemMod class to make the SQL call.
        return "successfully changed Item Type";
    }
    
    @Override
    public void addLocalChanges(String c){
        localChanges = c;
    }
    
    @Override
    public void resetLocalChanges() {
        localChanges = "";
    }
    
    @Override 
    public String getLocalChanges()
    {
        return localChanges;
    }
    
}
