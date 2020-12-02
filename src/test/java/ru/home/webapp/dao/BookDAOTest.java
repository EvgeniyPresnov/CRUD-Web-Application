package ru.home.webapp.dao;

import junit.framework.Assert;
import ru.home.webapp.domain.Book;
import org.junit.Test;
import ru.home.webapp.utils.ConnectionDBException;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class BookDAOTest {

    @Test
    public void checkTitleByBookIDTest() throws ConnectionDBException, DAOException, SQLException {
        String expected = "Nineteen Eighty-Four";
        String bookID = "5";

        IBookDAO bookDAO = new BookDAO();
        Book book= bookDAO.findBookById(bookID);
        String actual = book.getTitle();

        assertNotEquals(expected, actual);
    }

    @Test
    public void checkAuthorByBookIDTest() throws ConnectionDBException, DAOException, SQLException {
        String expected = "Jane Austen";
        String bookID = "2";

        IBookDAO bookDAO = new BookDAO();
        Book book= bookDAO.findBookById(bookID);
        String actual = book.getAuthor();

        assertEquals(expected, actual);
    }

    @Test
    public void checkBookForNotNullTest() {
        String bookID = "1";
        String title = "The Lord of the Rings";
        String author = "JRR Tolkien";

        Book book = new Book();
        book.setBookID(bookID);
        book.setTitle(title);
        book.setAuthor(author);

        assertNotNull(book);
    }

    @Test
    public void checkListTest() throws ConnectionDBException, DAOException, SQLException {
        IBookDAO bookDAO = new BookDAO();
        List<Book> listBooks = bookDAO.getListBooks();

        Assert.assertNotNull(listBooks);
        Assert.assertTrue(listBooks.size() > 0);
    }
}