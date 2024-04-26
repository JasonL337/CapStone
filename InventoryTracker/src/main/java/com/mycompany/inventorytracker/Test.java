/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventorytracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 * @author panoff_894755
 */
public class Test {
    public static void main (String[] args) throws ClassNotFoundException, IOException {
        
        Properties props = new Properties();
        System.out.println("Current working directory: " + System.getProperty("user.dir"));
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/com/mycompany/inventorytracker/gitIgnoreFiles/JDBInfo.env"))) {
            props.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Retrieve properties
        String endpoint = props.getProperty("DBURL");
        String username = props.getProperty("USERNAME");
        String password = props.getProperty("PASSWORD");
        
        System.out.println("url " + endpoint);
        System.out.println("username " + username);
        System.out.println("password " + password);
        //Class.forName("com.mysql.cj.jdbc.Driver");
        
    }
}
