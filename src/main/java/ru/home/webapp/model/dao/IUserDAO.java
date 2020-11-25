package ru.home.webapp.model.dao;

import ru.home.webapp.model.entities.User;
import ru.home.webapp.utils.ConnectionDBException;

/**
 * The interface defines an abstract API that performs CRUD operations
 *
 * @author Evgeniy Presnov
 */
public interface IUserDAO {
    void addUser(User user) throws DAOException, ConnectionDBException;
    User getUser(String userName, String password) throws DAOException, ConnectionDBException;
    void deleteUser(String userName) throws DAOException, ConnectionDBException;
}
