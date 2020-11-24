package ru.home.webapp.model.dao;

import ru.home.webapp.logging.LogHandler;
import ru.home.webapp.model.entities.Book;
import ru.home.webapp.utils.ConnectionDB;
import ru.home.webapp.utils.ConnectionDBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class that is an implementation of the IBookDAO interface
 *
 * @author Evgeniy Presnov
 */
public final class BookDAO implements IBookDAO {
    private LogHandler logHandler = new LogHandler(this);

    /**
     *
     * @param book that the user wants to add to the list
     */
    @Override
    public void addBook(Book book) throws DAOException, ConnectionDBException {
        final String ADD_BOOK = "INSERT INTO book VALUES(?, ?, ?)";

        try (PreparedStatement statement = ConnectionDB.getInstance()
                .getConnection()
                .prepareStatement(ADD_BOOK)) {

            statement.setString(1, book.getBookID());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getAuthor());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Could not add a book into the list", e);
        } catch (ConnectionDBException e) {
            e.printStackTrace();
            throw new ConnectionDBException("There is no database connection: ", e);
        }
    }

    /**
     *
     * @param book that the user wants to edit from the list
     */
    @Override
    public void updateBook(Book book) throws DAOException, ConnectionDBException {
        final String UPDATE_BOOK = "UPDATE book SET title = ?, author = ? WHERE book_id = ?";

        try (PreparedStatement statement = ConnectionDB.getInstance()
                .getConnection()
                .prepareStatement(UPDATE_BOOK)) {

            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getBookID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Could not edit the book from the list", e);
        } catch (ConnectionDBException e) {
            e.printStackTrace();
            throw new ConnectionDBException("There is no database connection: ", e);
        }
    }

    /**
     *
     * @param bookID is the ID of the book that the user wants to delete
     */
    @Override
    public void deleteBook(String bookID) throws DAOException, ConnectionDBException {
        final String DELETE_BOOK = "DELETE FROM book WHERE book_id = ?";

        try (PreparedStatement statement = ConnectionDB.getInstance()
                .getConnection()
                .prepareStatement(DELETE_BOOK)) {

            statement.setString(1, bookID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Could not delete the book from the list", e);
        } catch (ConnectionDBException e) {
            e.printStackTrace();
            throw new ConnectionDBException("There is no database connection: ", e);
        }
    }

    /**
     *
     * @return list of books
     */
    @Override
    public List<Book> getListBooks() throws DAOException, ConnectionDBException {
        final String GET_LIST_BOOKS = "SELECT book_id, title, author FROM book";
        List<Book> listBooks = new ArrayList<>();
        ResultSet resultSet;

        try (PreparedStatement statement = ConnectionDB.getInstance()
                .getConnection()
                .prepareStatement(GET_LIST_BOOKS)) {

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setBookID(resultSet.getString("book_id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                listBooks.add(book);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Could not get the list of books", e);
        } catch (ConnectionDBException e) {
            e.printStackTrace();
            throw new ConnectionDBException("There is no database connection: ", e);
        }
        return listBooks;
    }

    /**
     *
     * @param bookID is the ID of the book that the user wants to find
     * @return book
     */
    @Override
    public Book findBookById(String bookID) throws DAOException, ConnectionDBException {
        final String FIND_BOOK_BY_ID = "SELECT * FROM book WHERE book_id = ?";
        Book book =  new Book();
        ResultSet resultSet;

        try (PreparedStatement statement = ConnectionDB.getInstance()
                .getConnection()
                .prepareStatement(FIND_BOOK_BY_ID)) {

            statement.setString(1, bookID);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                book.setBookID(resultSet.getString("book_id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Could not find the book by id ", e);
        } catch (ConnectionDBException e) {
            e.printStackTrace();
            throw new ConnectionDBException("There is no database connection: ", e);
        }
        return book;
    }
}
