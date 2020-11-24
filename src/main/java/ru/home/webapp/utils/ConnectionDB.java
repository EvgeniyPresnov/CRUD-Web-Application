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

    private ConnectionDB() throws ConnectionDBException {
        try {
            loadProperties();
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new ConnectionDBException("Driver " + driver + " not found: ", e);
        }
    }

    public static synchronized ConnectionDB getInstance() throws ConnectionDBException {
        if (instance == null) {
            try {
                instance = new ConnectionDB();
            } catch (ConnectionDBException e) {
                e.printStackTrace();
                throw new ConnectionDBException("There is no database connection: ", e);
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() throws ConnectionDBException {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ConnectionDBException("The connection to database was not close: ", e);
        }
    }

    private void loadProperties() throws ConnectionDBException {
        Properties properties = new Properties();
        try {
            properties.load(ConnectionDB.class.getResourceAsStream("/db.properties"));
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            url = properties.getProperty("url");
            driver = properties.getProperty("driver");
        } catch (IOException e) {
            e.printStackTrace();
            throw new ConnectionDBException("Could not read the file: ", e);
        }
    }
}
