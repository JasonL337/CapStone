/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventorytracker;

/**
 *
 * @author panoff_894755
 */
public class ItemType extends Inventory {
    protected String name;
    protected String description;
    protected String localChanges;

    public ItemType(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    // Updating the values of the local changes (for that specific item type)
    public void addLocalChanges(String c)
    {
        // PERFORM SQL ACTION
        this.localChanges += c;
        addChanges(c);
    }
    
    public void resetLocalChanges()
    {
        this.localChanges = "";
    }
    
    public String getLocalChanges()
    {
        return this.localChanges;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        addLocalChanges("changed name: " + name);
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        addLocalChanges("changed description: " + description);
        this.description = description;
    }
    
    @Override
    public void addNewItemTypeToDatabase()
    {
        // Perform SQL action
        setOutput(toString());
        super.addNewItemTypeToDatabase();
    }
    
    @Override 
    public void RemoveItemTypeFromDatabase() {
        System.out.println(name + " of description " + description);
    }

    @Override
    public String toString() {
        return "ItemType{" + "name=" + name + ", description=" + description + ", localChanges=" + localChanges + '}';
    }
    
    
    
    
}
