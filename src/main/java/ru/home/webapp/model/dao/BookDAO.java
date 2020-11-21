package ru.home.webapp.model.dao;

import ru.home.webapp.logging.LogHandler;
import ru.home.webapp.model.entities.Book;
import ru.home.webapp.utils.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class that is an implementation of the IBookDAO interface
 *
 * @author Evgeniy Presnov
 */
public final class BookDAO implements IBookDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private LogHandler logHandler = new LogHandler(this);

    public BookDAO() {
        try {
            connection = ConnectionDB.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            logHandler.logError(e.fillInStackTrace());
        }
    }

    /**
     *
     * @param book that the user wants to add to the list
     */
    @Override
    public void addBook(Book book) {
        final String ADD_BOOK = "INSERT INTO book VALUES(?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(ADD_BOOK);
            preparedStatement.setString(1, book.getBookID());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getAuthor());
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
     * @param book that the user wants to edit from the list
     */
    @Override
    public void updateBook(Book book) {
        final String UPDATE_BOOK = "UPDATE book SET title = ?, author = ? WHERE book_id = ?";
        try {
            preparedStatement = connection.prepareStatement(UPDATE_BOOK);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getBookID());
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
     * @param bookID is the ID of the book that the user wants to delete
     */
    @Override
    public void deleteBook(String bookID) {
        final String DELETE_BOOK = "DELETE FROM book WHERE book_id = ?";
        try {
            preparedStatement = connection.prepareStatement(DELETE_BOOK);
            preparedStatement.setString(1, bookID);
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
     * @return list of books
     */
    @Override
    public List<Book> getListBooks() {
        final String GET_LIST_BOOKS = "SELECT book_id, title, author FROM book";
        List<Book> listBooks = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(GET_LIST_BOOKS);
            ResultSet resultSet = preparedStatement.executeQuery();

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
            logHandler.logError(e.fillInStackTrace());
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listBooks;
    }

    /**
     *
     * @param bookID is the ID of the book that the user wants to find
     * @return book
     */
    @Override
    public Book findBookById(String bookID) {
        final String FIND_BOOK_BY_ID = "SELECT * FROM book WHERE book_id = ?";
        Book book =  new Book();
        try {
            preparedStatement = connection.prepareStatement(FIND_BOOK_BY_ID);
            preparedStatement.setString(1, bookID);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                book.setBookID(resultSet.getString("book_id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
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
        return book;
    }
}
