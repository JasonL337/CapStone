/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventorytracker;

import java.awt.image.BufferedImage;

/**
 *
 * @author jampa
 */
public abstract class ItemMod {
    
    

    
     ////////////////////////////////////// GETTERS AND SETTTERS //////////////////////////////////////
    
    
    public static int getQuantity(String itemName) {
        return 0;
    }

    public static void setQuantity(String itemName, int quantity) {
        
    }

    public static String getItemName(String itemName) {
        return "";
    }

    public static void setItemName(String oldName, String itemName) {
        
    }

    public static double getPrice(String itemName) {
        return 0;
    }

    public static void setPrice(String itemName, double price) {
        
    }

    public static double getShelfLife(String itemName) {
        return 0;
    }

    public static void setShelfLife(String itemName, double shelfLife) {
        
    }

    public static String getItemDescription(String itemName) {
        return "";
    }

    public static void setItemDescription(String itemName, String itemDescription) {
        
    }

    public static String getColor(String itemName) {
        return "";
    }

    public static void setColor(String itemName, String color) {
        
    }

    public static BufferedImage getPicture(String itemName) {
        return null;
    }

    public static void setPicture(String itemName, BufferedImage picture) {
        
    }

    public static String getLocation(String itemName) {
        // SQL call
        return "";
    }

    public static void setLocation(String itemName, String location) {
        // SQL call
        
    }

    public static double getDaysLeftInStock(String itemName) {
        // SQL call
        return 0;
    }

    public static void setDaysLeftInStock(String itemName, double daysLeftInStock) {
        // SQL call
    }
    
    /////////////////// ITEM TYPE METHODS //////////////////////////////
    
    public static void setItemTypeName(String oldName) {
        
    }
    
    public static String getItemTypeName(String itemTypeName) {
        return "";
    }
    
    public static void setDescription(String oldName) {
        
    }
    
    public static String getDescription(String itemTypeName) {
        return "";
    }
    

}
