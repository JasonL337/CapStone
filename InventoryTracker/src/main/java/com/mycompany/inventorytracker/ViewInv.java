/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventorytracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
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
        
        String[] dbInfo = getDBInfo();
        
        try (Connection conn = DriverManager.getConnection(dbInfo[0], dbInfo[1], dbInfo[2])) {
            if (conn != null) {
                // Executes query that returns a string of all the item type names
                try (Statement statement = conn.createStatement()) {
                    // Executes query that returns a string of all the item type names
                    String[] types = returnItemTypes(statement);
                    for (String type : types) {
                        ArrayList<String[]> itemData = returnItemData(conn, type);
                        data.put(type, itemData);
                    }
                    // Close the result set and statement
                    // You can execute SQL queries here
                }
            }
        } catch (SQLException ex) {
            System.out.println("Failed to connect to the database: " + ex.getMessage());
        }
        
        /*
        TESTING TO MAKE SURE THE DATA IS CORRECTLY GATHERED AND ORGANIZED.
        for (String key: data.keySet()) {
            System.out.print(key);
            for (String[] dataSection : data.get(key)) {
                for (String dataPiece : dataSection) {
                    System.out.print(dataPiece + " ");
                }
                System.out.println();
            }
            System.out.println("\n");
        }
        */
        
        // Loops to go through the giant string of item types and data and the items and their data in the inventory class and put it in a Hashmap.
        return data;
    }
    
    private String[] returnItemTypes(Statement statement) throws SQLException {
        // Execute a query
        String sqlCommand = "SELECT COUNT(name) AS count from item_types";
        String[] names;
        try (ResultSet length = statement.executeQuery(sqlCommand)) {
            //resultSet.beforeFirst();
            //System.out.println(resultSet.getRow());
            if (length.next())
                names = new String[length.getInt("count")];
            else
                names = new String[0];
            
            length.close();
            
            
            ResultSet data = statement.executeQuery("SELECT name from item_types");
            // Process the result set
            int index = 0;
            while (data.next()) {
                // Retrieve data from the result set
                String name = data.getString("name");
                
                names[index] = name;
                // Do something with the data, printing the name
                index++;
            } 
            data.close();
        }
        return names;
    }
    
    private ArrayList<String[]> returnItemData(Connection conn, String itemType) throws SQLException {
        // getting all of the data from the items table by creating a prepared statement
        // and then injecting the itemType to the where clause.
        ArrayList<String[]> data = new ArrayList<>();
        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM items WHERE itemType = ?");
        ResultSet resultSet;
        
        pstmt.setString(1, itemType);
        resultSet = pstmt.executeQuery();
        
        ResultSetMetaData metaData = resultSet.getMetaData();

        // Get the number of columns
        int size = metaData.getColumnCount();
        
        while (resultSet.next()) {
            String[] rowData = new String[size];

            // Retrieve data from each column and store it in the array
            for (int i = 0; i < size; i++) {
                Object cell = resultSet.getObject(i + 1);
                rowData[i] = cell.toString(); // Column indices start from 1
            }
            
            data.add(rowData);
        }
        
        resultSet.close();
        return data;
    }
    
}
