/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventorytracker;

import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author panoff_894755
 */
public class AddToInv extends Inventory {
    
    public String addItem(String itemName, int quantity, double price, String itemDescription, int shelfLife, String color, BufferedImage pfp, String location, String itemType) throws SQLException {
        // Establishing DB connection using dbInfo of the parent class.
        String[] dbInfo = getDBInfo();
        Connection conn = DriverManager.getConnection(dbInfo[0], dbInfo[1], dbInfo[2]);
        
        // Using the getRowByName method to see if name of item already exists, if so, returns error message.
        String[] possibleName = getRowByName(itemName, "items", "itemName", conn);
        if (possibleName.length != 0) {
            String output = "Sorry, that name is already used for an inventory item in the item type: " + possibleName[possibleName.length - 1] + "ERROR CODE:NAME-IN-USE";
            addChangesToDB("ADD FAILURE: Failed to add item '" + itemName + "'. ERROR CODE:NAME-IN-USE", conn);
            conn.close();
            return "Sorry, that name is already used for an inventory item in the item type: " + possibleName[possibleName.length - 1] + "ERROR CODE:NAME-IN-USE";
        }
        
        // Using the getRowByName method to see if there exists an itemType that corresponds to that item.
        // If not, returns error message.
        possibleName = getRowByName(itemType, "item_types", "name", conn);
        if (possibleName.length == 0) {
            String output = "Sorry, no item type exists with that given item type! Would you like to add it to misc instead?" + "ERROR CODE:ITEM-TYPE-DNE";
            addChangesToDB("ADD FAILURE: Failed to add item '" + itemName +"'. ERROR CODE:ITEM-TYPE-DNE", conn);
            conn.close();
            return output;
        }
        

        // Adding to database
        String query = "INSERT INTO items (itemName, quantity, price, itemDescription, shelfLife, color, pfp, location, itemType) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        
        pstmt.setString(1, itemName);
        pstmt.setInt(2, quantity);
        pstmt.setDouble(3, price);
        pstmt.setString(4, itemDescription);
        pstmt.setInt(5, shelfLife);
        pstmt.setString(6, color);
        pstmt.setString(7, "" + pfp);
        pstmt.setString(8, location);
        pstmt.setString(9, itemType);
        
        
        // If successfully added to db, add to log, local changes, etc. and return success. If not, return error message.
        int affectedRows = pstmt.executeUpdate();
        pstmt.close();
        if (affectedRows > 0) {
            // adding local changes
            addLocalChanges("added item with properties: " + itemName + ": " + quantity + ": " + price + ": " + itemDescription + ": " + shelfLife + ": " + color + ": " + "" + ": " + location + ": " + itemType + "\n");
            // printing local changes
            printLocalChanges();
            // adding ovrall changes
            addChanges(getLocalChanges());
            // Resetting local changes (those of addToInv)
            resetLocalChanges();
            addChangesToDB("ADD SUCCESS: Successfully added item '" + itemName +"'", conn);
            conn.close();
            return "Successfully added item";
        } else {
            addChangesToDB("ADD FAILURE: Failed to add item '" + itemName +"'. ERROR CODE:UNKNOWN", conn);
            conn.close();
            return "Sorry, couldn't add item" + "ERROR CODE:UNKNOWN";
        }
    }
    
    public String addItemType(String name, String description) throws SQLException {
        // Establishing DB connection using dbInfo of the parent class.
        String[] dbInfo = getDBInfo();
        Connection conn = DriverManager.getConnection(dbInfo[0], dbInfo[1], dbInfo[2]);
        
        // Using the getRowByName method to see if name of the item type already exists, if so, returns error message.
        String[] possibleName = getRowByName(name, "item_types", "name", conn);
        if (possibleName.length != 0) {
            addChangesToDB("ADD FAILURE: Failed to add item type '" + name +"'. ERROR CODE:NAME-IN-USE", conn);
            conn.close();
            return "Sorry, that item type name is already used. ERROR CODE:NAME-IN-USE";
        }
        
        
        // Adding to database
        String query = "INSERT INTO item_types (id, name, description) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        
        pstmt.setInt(1, 0);
        pstmt.setString(2, name);
        pstmt.setString(3, description);
        
        
        // If successfully added to db, add to log, local changes, etc. and return success. If not, return error message.
        int affectedRows = pstmt.executeUpdate();
        pstmt.close();
        if (affectedRows > 0) {
            // adding local changes
            addLocalChanges("added item type with properties: " + name + ": " + description + "\n");
            printLocalChanges();
        
            addTypeStringTemp(getLocalChanges());
            resetLocalChanges();
            
            addChangesToDB("ADD SUCCESS: Successfully added item type '" + name + "'. ERROR CODE:NAME-IN-USE", conn);
            conn.close();
            return "successfully added Item Type";
        } else {
            addChangesToDB("ADD: Failed to add item type '" + name + "'. ERROR CODE:UKNOWN", conn);
            conn.close();
            return "Sorry, couldn't add item";
        }
        
    }
    
}
