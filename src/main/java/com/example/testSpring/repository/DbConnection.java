package com.example.testSpring.repository;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    public DbConnection() {
    }

    private static DbConnection instance = null;
    private Connection connection = null;

    public static DbConnection getInstance() {
        if (instance == null) {
            instance = new DbConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        if (connection == null) {
            openConnection();
        }
        return connection;
    }

    public boolean openConnection() {
        boolean state = false;
        if (connection == null) {

            try {
                connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/book_list", "root", "64125494944");
                state = !state;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return state;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
