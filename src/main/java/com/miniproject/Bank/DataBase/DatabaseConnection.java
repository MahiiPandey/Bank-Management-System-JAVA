package com.miniproject.Bank.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    protected static final String URL = "jdbc:mysql://localhost:3306/miniproject";
    protected static final String USER = "root";
    protected static final String PASSWORD = "darshit89";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
