package ru.home.webapp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class provides access to a database connection
 * and is implemented using a Singleton design pattern
 *
 * @author Evgeniy Presnov
 */
public final class ConnectionDB {
    private static volatile ConnectionDB instance;
    private Connection connection;

    private ConnectionDB() {
        try {
            final String URL = "jdbc:mysql://localhost:3306/crud_web_app";
            final String USER = "evgen";
            final String DRIVER = "com.mysql.jdbc.Driver";

            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, getPassword());

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    private String getPassword() {
        return "Evg3n_1995";
    }

    public static ConnectionDB getInstance() throws SQLException {
        if (instance == null) {
            instance = new ConnectionDB();
        }
        else if (instance.getConnection().isClosed()) {
            instance = new ConnectionDB();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}
