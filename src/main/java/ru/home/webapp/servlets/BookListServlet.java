package ru.home.webapp.servlets;

import ru.home.webapp.model.dao.BookDAO;
import ru.home.webapp.model.dao.IBookDAO;
import ru.home.webapp.model.entities.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * This class implements the logic of going to a page when a hyperlink 'bookList' is clicked.
 * The class provides a list of books as a table on a web page
 *
 * @author Evgeniy Presnov
 */
@WebServlet("/bookList")
public class BookListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> listBooks;
        IBookDAO bookDAO = new BookDAO();
        listBooks = bookDAO.getListBooks();

        req.setAttribute("listBooks", listBooks);

        req.getRequestDispatcher("/WEB-INF/view/bookList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
