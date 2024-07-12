package org.example.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final Logger logger = LoggerFactory.getLogger(DBConnection.class);

    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/rev_task_management";
        String user = "root";
        String password = "9754";
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            logger.info("Connection to the database established successfully.");
        } catch (ClassNotFoundException e) {
            logger.error("MySQL JDBC Driver not found.", e);
        } catch (SQLException e) {
            logger.error("Failed to establish a connection to the database.", e);
        }

        return con;
    }

    public static void closeConnection() {
    }
}
