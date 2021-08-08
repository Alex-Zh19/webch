package com.epam.webch.model.connection;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.testng.Assert.*;

public class ConnectionPoolTest {

    ConnectionPool pool;
    @BeforeMethod
    public void init(){
        pool=ConnectionPool.getInstance();
    }

    @Test
    public void testGetInstance() {
        assertNotNull(pool);
    }

    @Test
    public void testGetConnection() {
        Optional<ProxyConnection> connection=pool.getConnection();
        assertNotNull(connection.get());
    }
}