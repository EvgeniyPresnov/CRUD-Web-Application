package ru.home.webapp.servlets;

import org.apache.log4j.Logger;
import ru.home.webapp.model.dao.BookDAO;
import ru.home.webapp.model.dao.DAOException;
import ru.home.webapp.model.dao.IBookDAO;
import ru.home.webapp.model.entities.Book;
import ru.home.webapp.utils.ConnectionDBException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class implements the logic for editing the selected book from the list
 *
 * @author Evgeniy Presnov
 */
@WebServlet("/updateBook")
public class UpdateBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(UpdateBookServlet.class.getName());

    /*
     Display the edit page of the book
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookID = req.getParameter("bookID");

        IBookDAO bookDAO = new BookDAO();
        Book book = null;
        try {
            book = bookDAO.findBookById(bookID);
        } catch (DAOException | ConnectionDBException e) {
            e.printStackTrace();
        }

        req.setAttribute("book", book);

        logger.info("The book with bookID = " + bookID  +
                " , the title '" + book.getTitle() + "' and the author: " + book.getAuthor());

        req.getRequestDispatcher("/WEB-INF/view/updateBook.jsp").forward(req, resp);
    }

    /*
     After the user has edited information about book and clicked Submit.
     This method will be executed
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String bookID = req.getParameter("bookID");
        String title = req.getParameter("title");
        String author = req.getParameter("author");

        Book book = new Book();
        book.setBookID(bookID);
        book.setTitle(title);
        book.setAuthor(author);

        IBookDAO bookDAO = new BookDAO();
        try {
            bookDAO.updateBook(book);
        } catch (DAOException | ConnectionDBException e) {
            e.printStackTrace();
        }
        req.setAttribute("book", book);

        logger.info("The book with bookID = " + bookID + " was updated to " +
                " new title '" + book.getTitle() + "' and new author " + book.getAuthor());

        resp.sendRedirect(req.getContextPath() + "/bookList");
    }
}
