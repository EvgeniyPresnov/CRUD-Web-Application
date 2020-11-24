package ru.home.webapp.model.dao;

import ru.home.webapp.logging.LogHandler;
import ru.home.webapp.model.entities.User;
import ru.home.webapp.utils.ConnectionDB;
import ru.home.webapp.utils.ConnectionDBException;

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
    public void addUser(User user) throws DAOException, ConnectionDBException {
        final String ADD_USER = "INSERT INTO user VALUES(?, ?)";

        try (PreparedStatement statement = ConnectionDB.getInstance()
                .getConnection()
                .prepareStatement(ADD_USER)) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            //e.printStackTrace();
            throw new DAOException("Could not add a new user ", e);
        } catch (ConnectionDBException e) {
            //e.printStackTrace();
            throw new ConnectionDBException("There is no database connection: ", e);
        }
    }

    /**
     *
     * @param password user password
     * @return the user by name and password
     */
    @Override
    public User getUser(String userName, String password) throws DAOException, ConnectionDBException {
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
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Could not get the user: ", e);
        } catch (ConnectionDBException e) {
            e.printStackTrace();
            throw new ConnectionDBException("There is no database connection: ", e);
        }
        return user;
    }

    /**
     *
     * @param userName name of the user who will be deleted from database
     */
    @Override
    public void deleteUser(String userName) throws DAOException {
        final String DELETE_USER = "DELETE FROM user WHERE name = ?";

        try (PreparedStatement statement = ConnectionDB.getInstance()
                .getConnection()
                .prepareStatement(DELETE_USER)) {

            statement.setString(1, userName);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Could not delete the user: ", e);
        } catch (ConnectionDBException e) {
            e.printStackTrace();
        }
    }
}
