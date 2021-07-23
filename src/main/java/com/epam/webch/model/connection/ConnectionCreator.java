package com.epam.webch.model.connection;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.epam.webch.model.connection.DataBasePropertyReader.*;

class ConnectionCreator {

    private static final Logger logger = LogManager.getLogger();

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            logger.log(Level.FATAL, "Cannot register database driver {}", e.getMessage());
            throw new ExceptionInInitializerError("Cannot register database driver ");
        }
    }

    private ConnectionCreator() {}
    public static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
}

