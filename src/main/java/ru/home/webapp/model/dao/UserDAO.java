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
    private Connection connection;
    private PreparedStatement preparedStatement;

    public UserDAO() throws SQLException {
        connection = ConnectionDB.getInstance().getConnection();
    }

    /**
     *
     * @param user the user who will be added to the database
     */
    @Override
    public void addUser(User user) {
        final String ADD_USER = "INSERT INTO user VALUES(?, ?)";
        try {
            preparedStatement = connection.prepareStatement(ADD_USER);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            logHandler.logError(e.fillInStackTrace());
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param userName
     * @param password
     * @return the user by name and password
     */
    @Override
    public User getUser(String userName, String password) {
        User user = new User();
        final String GET_USER = "SELECT * FROM user WHERE name = ? and password = ?";
        try {
            preparedStatement = connection.prepareStatement(GET_USER);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user.setName(resultSet.getString(1));
                user.setPassword(resultSet.getString(2));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
            logHandler.logError(e.fillInStackTrace());
        } finally {
            try {
                preparedStatement.close();
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
        try {
            preparedStatement = connection.prepareStatement(DELETE_USER);
            preparedStatement.setString(1, userName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            logHandler.logError(e.fillInStackTrace());
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
