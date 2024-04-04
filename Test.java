/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventorytracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author panoff_894755
 */
public class Test {
    public static void main (String[] args) throws ClassNotFoundException {
        String dbURL = "no data";
        String username = "no data";
        String password = "no data";

        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            if (conn != null) {
                System.out.println("Connected to the database!");
                // You can execute SQL queries here
            }
        } catch (SQLException ex) {
            System.out.println("Failed to connect to the database: " + ex.getMessage());
        }
    }
}
