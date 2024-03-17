package com.example;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main (String[] args) {
        //connect to the database
        String url = "jdbc:postgresql://localhost:5432/Assignment 3 Database";
        String user = "postgres"; 
        String password = "Rachilio22!";
       
        try{
            
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password); 
            System.out.println("fs");
            if(connection != null){
                System.out.println("Connected to the database! :)");
            }else{
                System.out.println("Failed to connect to the database :("); 
            }

        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
