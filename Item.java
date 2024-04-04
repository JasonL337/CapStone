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
public class Item extends ItemType{
    private int quantity;
    private String itemName;
    private double price;
    private double shelfLife;
    private String itemDescription;
    private String color;
    private BufferedImage picture;
    private String location;
    private double daysLeftInStock;

    public Item(int quantity, String itemName, double price, double shelfLife, String itemDescription, String color, BufferedImage picture, String location, double daysLeftInStock, String name, String description) {
        super(name, description);
        this.quantity = quantity;
        this.itemName = itemName;
        this.price = price;
        this.shelfLife = shelfLife;
        this.itemDescription = itemDescription;
        this.color = color;
        this.picture = picture;
        this.location = location;
        this.daysLeftInStock = daysLeftInStock;
    }
    
    @Override
    public void addLocalChanges(String c)
    {
        // Perform SQL action
        this.localChanges += c;
        addChanges(c);
    }
    
    @Override
    public void resetLocalChanges()
    {
        this.localChanges = "";
    }
    
    @Override
    public String getLocalChanges()
    {
        return this.localChanges;
    }
    
    
    // This overriden method actually makes the SQL call and updates the database.
    @Override
    public void addNewItemToDatabase()
    {
        // Perform SQL action
        setOutput(toString());
        super.addNewItemToDatabase();
    }

    @Override
    public String toString() {
        return "Item{" + "quantity=" + quantity + ", itemName=" + itemName + ", price=" + price + ", shelfLife=" + shelfLife + ", itemDescription=" + itemDescription + ", color=" + color + ", picture=" + picture + ", location=" + location + ", daysLeftInStock=" + daysLeftInStock + '}';
    }
    
    
    // These are the methods for the changes that are made every hour to an item.
   
    
    @Override 
    public String getChanges()
    {
        return localChanges;
    }
    
    @Override 
    public void RemoveItemFromDatabase() {
        System.out.println(itemName + " of description " + itemDescription);
    }
    
    ////////////////////////////////////// GETTERS AND SETTTERS //////////////////////////////////////
    
    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getShelfLife() {
        return this.shelfLife;
    }

    public void setShelfLife(double shelfLife) {
        this.shelfLife = shelfLife;
    }

    public String getItemDescription() {
        return this.itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BufferedImage getPicture() {
        return this.picture;
    }

    public void setPicture(BufferedImage picture) {
        this.picture = picture;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getDaysLeftInStock() {
        return this.daysLeftInStock;
    }

    public void setDaysLeftInStock(double daysLeftInStock) {
        this.daysLeftInStock = daysLeftInStock;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }
    
    
    

}
