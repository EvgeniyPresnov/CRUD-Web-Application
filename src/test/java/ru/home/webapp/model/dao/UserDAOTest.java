package ru.home.webapp.model.dao;

import ru.home.webapp.model.entities.User;

import java.net.PasswordAuthentication;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserDAOTest {
    private Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/crud_web_app";
    private static final String USER = "evgen";
    private static final String PASSWORD = "Evg3n_1995";

    @Before
    public void before() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkPasswordAdminUserTest() throws SQLException {
        IUserDAO userDAO = new UserDAO();

        String userName = "admin";
        String password = "1234";
        User user = userDAO.getUser(userName, password);

        String expectedPassword = "1234";
        String actualPassword = user.getPassword();
        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    public void checkNameDefaultUserTest() throws SQLException {
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
