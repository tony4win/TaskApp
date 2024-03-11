/**
 * Allows connection to Database
 * Used for MySqlTaskDAO
 * */
package com.dkit.oop.sd2.DAOs;

import java.sql.*;

import com.dkit.oop.sd2.Exceptions.DaoException;

public class MySqlDao {

    public Connection getConnection() throws DaoException {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/task_db";
        String username = "root";
        String password = "";
        Connection connection = null;

        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        }
        catch (ClassNotFoundException e) {
            System.out.println("Failed to find Driver Class: " + e.getMessage());
            System.exit(1);
        }
        catch (SQLException e) {
            System.out.println("Connection Failed: " + e.getMessage());
            System.exit(2);
        }

        return connection;

    }

    public void freeConnection(Connection connection) throws DaoException {

        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
        }
        catch (SQLException e) {
            System.out.println("Failed to Free Connection: " + e.getMessage());
            System.exit(1);
        }

    }
}