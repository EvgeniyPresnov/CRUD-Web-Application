package ru.home.webapp.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * This class provides access to a database connection
 *
 * @author Evgeniy Presnov
 */
public final class ConnectionDB {
    private static ConnectionDB instance = null;
    private Connection connection = null;
    private static String user = null;
    private static String password = null;
    private static String url = null;
    private static String driver = null;

    private ConnectionDB() {
        try {
            loadProperties();
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public static synchronized ConnectionDB getInstance() {
        if (instance == null) {
            instance = new ConnectionDB();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection()  {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadProperties() {
        Properties properties = new Properties();
        try {
            properties.load(ConnectionDB.class.getResourceAsStream("/db.properties"));
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            url = properties.getProperty("url");
            driver = properties.getProperty("driver");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
