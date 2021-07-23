package com.epam.webch.model.connection;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Optional;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.epam.webch.model.connection.DataBasePropertyReader.DEFAULT_POOL_SIZE;

public class ConnectionPool {
    private static ConnectionPool instance;
    private static final Logger logger = LogManager.getLogger();
    private static final AtomicBoolean isCreated = new AtomicBoolean(false);
    private BlockingDeque<ProxyConnection> freeConnection;
    private BlockingDeque<ProxyConnection> occupiedConnection;
    private final int MAX_COUNT_OF_CREATION_ADDITIONAL_CONNECTION = 6;

    private ConnectionPool() {
        freeConnection = new LinkedBlockingDeque<>();
        occupiedConnection = new LinkedBlockingDeque<>();

        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                Connection connection = ConnectionCreator.createConnection();
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                freeConnection.put(proxyConnection);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "sqlException while Connection creation occurred: {}", e.getMessage());
            } catch (InterruptedException e) {
                logger.log(Level.ERROR, "InterruptedException while Connection creation occurred: {}", e.getMessage());
            }
        }
        if (freeConnection.isEmpty()) {
            logger.log(Level.FATAL, "Connections cannot be created");
            throw new RuntimeException("Connections cannot be created");
        } else if (freeConnection.size() == DEFAULT_POOL_SIZE) {
            logger.log(Level.INFO, "Connection pool successfully created");
        } else if (freeConnection.size() < DEFAULT_POOL_SIZE) {
            int count = 0;
            while (freeConnection.size() < DEFAULT_POOL_SIZE) {
                addAdditionalConnections();
                count++;
                if (count == MAX_COUNT_OF_CREATION_ADDITIONAL_CONNECTION) {
                    logger.log(Level.FATAL, "additional Connections cannot be created");
                    throw new RuntimeException("additional Connections cannot be created");
                }
            }
        }
    }

    public static ConnectionPool getInstance() {
        while (instance == null) {
            if (isCreated.compareAndSet(false, true)) {
                instance = new ConnectionPool();
            }
        }
        return instance;
    }

    public Optional<ProxyConnection> getConnection() {
        Optional<ProxyConnection> proxyConnection = Optional.empty();
        try {
            ProxyConnection proxy = freeConnection.take();
            proxyConnection = Optional.of(proxy);
            occupiedConnection.put(proxy);
        } catch (InterruptedException exception) {
            logger.log(Level.ERROR, "InterruptedException while getting free connection: {}", exception.getMessage());
        }
        return proxyConnection;
    }


    public void realiseConnection(Connection connection) {
        if (!(connection instanceof ProxyConnection)) {
            logger.log(Level.ERROR, "unknown connection detected");
            throw new RuntimeException("unknown connection detected");
        }
        if (occupiedConnection.removeFirstOccurrence(connection)) {
            try {
                freeConnection.put((ProxyConnection) connection);
            } catch (InterruptedException exception) {
                logger.log(Level.ERROR, "InterruptedException while realising connection: {}", exception.getMessage());
            }
        }else{
            logger.log(Level.ERROR, "connection does not exist in pool");
            throw new RuntimeException("connection does not exist in pool");
        }
    }

    public void deleteConnectionPool(){
        while(!freeConnection.isEmpty()){
           try{
               freeConnection.take().reallyClose();
           } catch (SQLException e) {
               logger.log(Level.ERROR, "sqlException while Connection pool deleting occurred: {}", e.getMessage());
           } catch (InterruptedException e) {
               logger.log(Level.ERROR, "InterruptedException while Connection pool deleting  occurred: {}", e.getMessage());
           }
        }
        while (!occupiedConnection.isEmpty()){
            try{
                occupiedConnection.take().reallyClose();
            } catch (SQLException e) {
                logger.log(Level.ERROR, "sqlException while Connection pool deleting occurred: {}", e.getMessage());
            } catch (InterruptedException e) {
                logger.log(Level.ERROR, "InterruptedException while Connection pool deleting  occurred: {}", e.getMessage());
            }
        }
        deregisterDrivers();
    }





    private void addAdditionalConnections() {
        int countOfConnectionsToCreate = DEFAULT_POOL_SIZE - freeConnection.size();
        for (int i = 0; i < countOfConnectionsToCreate; i++) {
            try {
                Connection connection = ConnectionCreator.createConnection();
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                freeConnection.put(proxyConnection);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "sqlException while additional Connection creation occurred, {}", e.getMessage());
            } catch (InterruptedException e) {
                logger.log(Level.ERROR, "InterruptedException while additional Connection creation occurred, {}", e.getMessage());
            }
        }
    }

    private void deregisterDrivers() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "SQLException while deregistration drivers {}",e.getMessage());
            }
        }
    }

}
