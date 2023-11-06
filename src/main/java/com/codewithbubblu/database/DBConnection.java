package com.codewithbubblu.database;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnection {
    Connection connection;
    private static final String connectionUrl="jdbc:mysql://localhost:3306/javasql";
    private static final String username="root";
    private static final String password="root";

    public DBConnection(){
        connection=getDBConnection();
    }

    public static Connection getDBConnection() {
        Connection connection=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection(connectionUrl,username,password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
