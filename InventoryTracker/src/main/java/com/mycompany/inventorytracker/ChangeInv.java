/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventorytracker;

import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author panoff_894755
 */
// For changing, it works similar to adding. However, it passes in the name of the item type or item you're changing and then goes from there. 
// This class is also for minimizing the amount the database has to retrieve and write to by checking locally if the changes have
// actually been changed and where. 
public class ChangeInv extends Inventory {
    
    // These are the two main variables of this class. The ArrayLists ChangedIDs and Changed IDTypes
    // holds the ids of all the items and item types that have been changed in the table. The Map 
    // changedVals holds all the original values of the cells in the ID (the key) that has been changed.
    private ArrayList<Integer> changedIDs = new ArrayList<>();
    private Map<Integer, String[]> changedVals = new HashMap<>();
    private Map<Integer, String[]> origVals = new HashMap<>();
        
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
    
    
    private void addToChangeID(int id, String itemName, int quantity, double price, String itemDescription, int shelfLife, String color, String pfp, String location, int itemTypeID) {
        if (!changedIDs.contains(id)) {
            changedIDs.add(id);
            origVals.put(id, new String[]{itemName, "" + quantity, "" + price, itemDescription, "" + shelfLife, color, pfp, location, "" + itemTypeID});
        }
    }
    
    /*
    1. Make an item id be the primary identification type.
2. Make an item type id be the primary identificaiton type.
3. Include data on which ids have been changed and what values in them have been changed, then update on the db as necessary.
4. If there is a conflict (someone else modofied the same thing before you could push and you don't have the latest), pop up an error saying what has been changed that doesn't match what you have locally on the og.
5. So, cross check the original values thta are now changed with the db and throw a pop up if they don't match.
 In the popup, show "all other vals have been changed successfully tho"
6. Handle Item Type name change checking
    */
    
    // Method checks if the row just changed is actually changed and returns true if it is, false if it isn't. If it is changed, it adds all the changed values to changedVals.
    private boolean checkChange(int id, String itemName, int quantity, double price, String itemDescription, int shelfLife, String color, String pfp, String location, int itemTypeID) {
        String[] orig = origVals.get(id);
        String[] changes = new String[orig.length];
        boolean mod = false;
        if (!orig[0].equals(itemName)) { changes[0] = itemName; mod = true;}
        if (!orig[1].equals("" + quantity)) { changes[1] = "" + quantity; mod = true;}
        if (!orig[2].equals("" + price)) { changes[2] = "" + price; mod = true;}
        if (!orig[3].equals(itemDescription)) { changes[3] = itemDescription; mod = true;}
        if (!orig[4].equals("" + shelfLife)) { changes[4] = "" + shelfLife; mod = true;}
        if (!orig[5].equals(color)) { changes[5] = color; mod = true;}
        if (!orig[6].equals(pfp)) { changes[6] = pfp; mod = true;}
        if (!orig[7].equals(location)) { changes[7] = location; mod = true;}
        if (!orig[8].equals("" + itemTypeID)) { changes[8] = "" + itemTypeID; mod = true;}
        changedVals.put(id, changes);
        if (!mod) {changedIDs.remove(changedIDs.indexOf(id)); changedVals.remove(id); origVals.remove(id); return false;}
        return true;
    }
    
    
    // THis method actually makes the SQL calls to update the database and also returns the popup about an item type name changed or something you've changed being changed.
    public void changeItems() throws SQLException {
        // Establishing DB connection using dbInfo of the parent class.
        String[] dbInfo = getDBInfo();
        Connection conn = DriverManager.getConnection(dbInfo[0], dbInfo[1], dbInfo[2]);
        
        // Here I check the db against the og vals and modify checkedChangedVals to not include the change if it doesn't match
        Map<Integer, String[]> checkedChangedVals = new HashMap<>();
        for (int i : changedVals.keySet())
        {
            checkedChangedVals.put(i, changedVals.get(i));
        }
        
        // This is where I do the big long logic to figure out what the string of the query is. Like if the first val of array in changed Vals is changed, then add this to it, etc.
        String query = "UPDATE items set ";
        PreparedStatement pstmt = conn.prepareStatement(query);
    }
    
   public Map<String, Map<String, Object>> changeItem(String[] originalName, int[] id, int[] quantity, String[] itemName,
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
