package com.epam.webch.model.connection;

import java.util.ResourceBundle;

class DataBasePropertyReader {
    static final String USER;
    static final String PASSWORD;
    static final int DEFAULT_POOL_SIZE;
    static final String URL;
    static final String TIMEZONE;
    static final String DRIVER;

    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("\\settings\\database");
        USER = resourceBundle.getString("user");
        PASSWORD = resourceBundle.getString("password");
        DEFAULT_POOL_SIZE =Integer.parseInt(resourceBundle.getString("defaultPoolSize"));
        URL=resourceBundle.getString("url");
        TIMEZONE = resourceBundle.getString("serverTimezone");
        DRIVER = resourceBundle.getString("driver");
    }
    private DataBasePropertyReader(){
    }
}
