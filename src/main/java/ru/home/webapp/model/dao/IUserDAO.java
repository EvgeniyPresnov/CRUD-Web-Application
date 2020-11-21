package ru.home.webapp.model.dao;

import ru.home.webapp.model.entities.User;

/**
 * The interface defines an abstract API that performs CRUD operations
 *
 * @author Evgeniy Presnov
 */
public interface IUserDAO {
    void addUser(User user);
    User getUser(String userName, String password);
    void deleteUser(String userName);
}
