package ru.home.webapp.model.dao;

import ru.home.webapp.logging.LogHandler;
import ru.home.webapp.model.entities.User;
import ru.home.webapp.utils.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class that is an implementation of the IUserDAO interface
 *
 * @author Evgeniy Presnov
 */
public final class UserDAO implements IUserDAO {
    private LogHandler logHandler = new LogHandler(this);

    /**
     *
     * @param user the user who will be added to the database
     */
    @Override
    public void addUser(User user) {
        final String ADD_USER = "INSERT INTO user VALUES(?, ?)";

        try (PreparedStatement statement = ConnectionDB.getInstance()
                .getConnection()
                .prepareStatement(ADD_USER)) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param password user password
     * @return the user by name and password
     */
    @Override
    public User getUser(String userName, String password) {
        final String GET_USER = "SELECT * FROM user WHERE name = ? AND password = ?";
        User user = new User();
        ResultSet resultSet = null;

        try (PreparedStatement statement = ConnectionDB.getInstance()
                .getConnection()
                .prepareStatement(GET_USER)) {

            statement.setString(1, userName);
            statement.setString(2, password);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println("here is problem");
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                } else {
                    System.out.println("resultSet is null");
                    // logging that resultSet is null
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    /**
     *
     * @param userName name of the user who will be deleted from database
     */
    @Override
    public void deleteUser(String userName) {
        final String DELETE_USER = "DELETE FROM user WHERE name = ?";

        try (PreparedStatement statement = ConnectionDB.getInstance()
                .getConnection()
                .prepareStatement(DELETE_USER)) {

            statement.setString(1, userName);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
