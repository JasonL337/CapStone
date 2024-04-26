/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventorytracker;

// Class is the parent class to item type and holds methods to print out inventory data.

import java.awt.image.BufferedImage;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Date;

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
        try (FileReader reader = new FileReader("src/main/java/com/mycompany/inventorytracker/gitIgnoreFiles/JDBInfo.env")) {
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
    
    protected void addChangesToDB(String c, Connection conn) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO log (Date, Entries) VALUES (?, ?)");
        pstmt.setTimestamp(1, new java.sql.Timestamp(new Date().getTime()));
        pstmt.setString(2, c);
        pstmt.executeUpdate();
    }
    
    public ArrayList<String[]> getDBLog() throws SQLException {
        ArrayList<String[]> loggedData = new ArrayList<>();
        String[] dbInfo = getDBInfo();
        Connection conn = DriverManager.getConnection(dbInfo[0], dbInfo[1], dbInfo[2]);
        Statement statement = conn.createStatement();
        ResultSet log = statement.executeQuery("SELECT * FROM log");
        while (log.next()) {
            loggedData.add(new String[]{log.getTimestamp(1).toString(), log.getString(2)});
        }
        return loggedData;
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
    
    public ArrayList<String> getChangesRaw() {
        return CHANGES;
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
    
    // Returns an array of strings that are the data points/cells for a certain row that has a certain value
    // (name) for a certain column in that row (columnName)
    protected String[] getRowByName(String name, String tableName, String columnName, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + tableName + " WHERE " + columnName + " = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet resultSet;
        
        pstmt.setString(1, name);
        resultSet = pstmt.executeQuery();
        
        int columnCount =  resultSet.getMetaData().getColumnCount();
        
        String[] data = new String[columnCount];
        if (resultSet.next()) 
        {
            for (int i = 1; i <= columnCount; i++)
            {
                data[i - 1] = resultSet.getString(i);
            }
        }
        else
            return new String[0];
        
        return data;
    }
    
    
    
}
