package ru.home.webapp.dao;

import ru.home.webapp.domain.Book;
import ru.home.webapp.utils.ConnectionDBException;

import java.util.List;

/**
 * The interface defines an abstract API that performs CRUD operations
 *
 * @author Evgeniy Presnov
 */
public interface IBookDAO {
    void addBook(Book book) throws DAOException, ConnectionDBException;
    void updateBook(Book book) throws DAOException, ConnectionDBException;
    void deleteBook(String bookID) throws DAOException, ConnectionDBException;
    List<Book> getListBooks() throws DAOException, ConnectionDBException;
    Book findBookById(String bookID) throws DAOException, ConnectionDBException;
}
