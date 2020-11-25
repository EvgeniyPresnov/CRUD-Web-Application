package ru.home.webapp.servlets;

import org.apache.log4j.Logger;
import ru.home.webapp.model.dao.BookDAO;
import ru.home.webapp.model.dao.DAOException;
import ru.home.webapp.model.dao.IBookDAO;
import ru.home.webapp.utils.ConnectionDBException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class implements the logic of deleting a book from the list
 *
 * @author Evgeniy Presnov
 */
@WebServlet("/deleteBook")
public class DeleteBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(DeleteBookServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String bookID = req.getParameter("bookID");
        IBookDAO bookDAO = new BookDAO();

        try {
            bookDAO.deleteBook(bookID);
        } catch (DAOException | ConnectionDBException e) {
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("The book with bookID = " + bookID +  " was deleted from the list");
        resp.sendRedirect(req.getContextPath() + "/bookList");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }
}
