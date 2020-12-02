package ru.home.webapp.dao;

import ru.home.webapp.domain.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.home.webapp.utils.ConnectionDB;
import ru.home.webapp.utils.ConnectionDBException;

public class UserDAOTest {
    private Connection connection;
    private static String user = null;
    private static String password = null;
    private static String url = null;
    private static String driver = null;


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

    @Before
    public void before() throws ConnectionDBException {
        try {
            loadProperties();
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkPasswordAdminUserTest() throws ConnectionDBException, DAOException, SQLException {
        IUserDAO userDAO = new UserDAO();

        String userName = "admin";
        String password = "1234";
        User user = userDAO.getUser(userName, password);

        String expectedPassword = "1234";
        String actualPassword = user.getPassword();
        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    public void checkNameDefaultUserTest() throws ConnectionDBException, DAOException, SQLException {
        IUserDAO userDAO = new UserDAO();
        User user = new User();

        String userName = "admin";
        String password = "1234";
        String expectedUser = "temp";
        String actualUser = user.getName();

        user = userDAO.getUser(userName, password);
        assertNotEquals(expectedUser, actualUser);
    }

    @After
    public void after() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
