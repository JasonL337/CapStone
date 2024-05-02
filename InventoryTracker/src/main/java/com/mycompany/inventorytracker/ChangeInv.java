/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventorytracker;

import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
    
    
   public Map<String, Map<String, Object>> changeItem(String[] originalName, int[] quantity, String[] itemName,
                                                        double[] price, int[] shelfLife, String[] itemDescription,
                                                        String[] color, String[] picture, String[] location,
                                                        int[] daysLeftInStock, ResultSet resultSet) throws SQLException {
        Map<String, Map<String, Object>> changes = new HashMap<>();

        while (resultSet.next()) {
            String currentOriginalName = resultSet.getString("name");
            int index = getIndex(originalName, currentOriginalName);
            if (index != -1) {
                Map<String, Object> changeDetails = new HashMap<>();
                if (quantity != null) changeDetails.put("quantity", quantity[index]);
                if (itemName != null) changeDetails.put("itemName", itemName[index]);
                if (price != null) changeDetails.put("price", price[index]);
                if (shelfLife != null) changeDetails.put("shelfLife", shelfLife[index]);
                if (itemDescription != null) changeDetails.put("itemDescription", itemDescription[index]);
                if (color != null) changeDetails.put("color", color[index]);
                if (picture != null) changeDetails.put("picture", picture[index]);
                if (location != null) changeDetails.put("location", location[index]);
                if (daysLeftInStock != null) changeDetails.put("daysLeftInStock", daysLeftInStock[index]);
                
                changes.put(currentOriginalName, changeDetails);
            }
            }

        return changes;
    }

    private int getIndex(String[] originalName, String currentOriginalName) {
        for (int i = 0; i < originalName.length; i++) {
            if (originalName[i].equals(currentOriginalName)) {
                return i;
            }
        }
        return -1;
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
