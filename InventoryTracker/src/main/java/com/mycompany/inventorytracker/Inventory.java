/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventorytracker;

// Class is the parent class to item type and holds methods to print out inventory data.

import java.awt.image.BufferedImage;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public abstract class Inventory {
    
    
    // Output is the current String that is printed when a new database item/item type is created and added.
    protected String output = "";
    
    // Short string that keeps track of all the changes to items and item types over the past hour.
    private final static ArrayList<String> CHANGES = new ArrayList<>();
    
    public static String tempTypes = "";
    public static String tempItems = "";
    
    protected String localChanges = "";
    
    protected String[] getDBInfo() {
        Properties props = new Properties();
        try (FileReader reader = new FileReader(".env")) {
            props.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Retrieve properties
        String endpoint = props.getProperty("DBURL");
        String username = props.getProperty("USERNAME");
        String password = props.getProperty("PASSWORD");
        return new String[]{endpoint, username, password};
    } 
    
    public void addChanges(String c)
    {
        CHANGES.add(c);
    }
    
    // Tmporary, will be taken out later
    protected void addItemStringTemp(String c) {
        tempItems += c;
    }
    
    protected void addTypeStringTemp(String c) {
        tempTypes += c;
    }
    
    public void resetChanges()
    {
        CHANGES.clear();
    }
    
    public String getChanges()
    {
        String log = "log:\n";
        for (String i : CHANGES)
        {
            log += i;
        }
        return log;
        
    }
    
    public void addLocalChanges(String c){
        localChanges = c;
    }
    
    public void resetLocalChanges() {
        localChanges = "";
    }
    
    public String getLocalChanges()
    {
        return localChanges;
    }
    
    protected void printLocalChanges() {
        System.out.println("lc: " + localChanges);
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
    
        // Parent method to print out the output which is updated in the other classes.
    public void addNewItemToDatabase()
    {
        output += "\nsuccessfully added item";
        System.out.println(output);
    }
    
    // Same as add new item, but with item type
    public void addNewItemTypeToDatabase()
    {
        output += "\nsuccessfully added item type";
        System.out.println(output);
    }
    
    // Parent functions for removing items or item types.
    public void RemoveItemFromDatabase()
    {
        output += "\nsuccessfully removed item";
        System.out.println(output);
    }
    
    public void RemoveItemTypeFromDatabase()
    {
        output += "\nsuccessfully removed item type";
        System.out.println(output);
    }
    
    // Methods to get database values
    
    protected int getQuantity(String itemName) {
        return 0;
    }

    protected String getItemName(String itemName) {
        return "";
    }
    
    protected double getPrice(String itemName) {
        return 0;
    }

    protected double getShelfLife(String itemName) {
        return 0;
    }

    protected String getItemDescription(String itemName) {
        return "";
    }

    protected String getColor(String itemName) {
        return "";
    }

    protected BufferedImage getPicture(String itemName) {
        return null;
    }

    protected String getLocation(String itemName) {
        // SQL call
        return "";
    }

    protected double getDaysLeftInStock(String itemName) {
        // SQL call
        return 0;
    }
    
    protected String getItemTypeName(String itemTypeName) {
        return "";
    }
    
    protected String getDescription(String itemTypeName) {
        return "";
    }
    
    
    
}
