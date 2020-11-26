package ru.home.webapp.servlets;

import org.apache.log4j.Logger;
import ru.home.webapp.dao.BookDAO;
import ru.home.webapp.dao.DAOException;
import ru.home.webapp.dao.IBookDAO;
import ru.home.webapp.domain.Book;
import ru.home.webapp.utils.ConnectionDBException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class implements the logic of going to a page when a hyperlink 'createOrder' is clicked.
 * This class allows the user to add a book to the list from the form
 *
 * @author Evgeniy Presnov
 */
@WebServlet("/addBook")
public class AddBook extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(AddBook.class.getName());

    /*
     Display the page for adding a book
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/addBook.jsp").forward(req, resp);
    }

    /**
     When the user has entered information about book and clicks Submit.
     This method will be called
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String bookID = req.getParameter("bookID");
        String title = req.getParameter("title");
        String author = req.getParameter("author");

        /*
         Checking for filling in the fields on the form.
         If at least one field is empty, user will see the warning message ...
         */
        if (bookID.equals("") || title.equals("")|| author.equals("")) {
            logger.warn("The field ID or Title or Author is empty");

            PrintWriter out = resp.getWriter();
            out.println(getHTMLPage("The field ID or Title or Author is empty. Please, try it again"));
            out.close();
        }
        /*
         ... Otherwise, the book will be added to the list
         */
        else {
            Book book = new Book();
            book.setBookID(bookID);
            book.setTitle(title);
            book.setAuthor(author);

            IBookDAO bookDAO = new BookDAO();
            try {
                bookDAO.addBook(book);
            } catch (DAOException | ConnectionDBException e) {
                e.printStackTrace();
                logger.error(e);
            }
            logger.warn("The book '" + title + "' written by " + author + " was added to the list");

            req.setAttribute("book", book);
            resp.sendRedirect(req.getContextPath() + "/bookList");
        }
    }

    /**
     *
     * @param message on the page
     * @return HTML page
     */
    private String getHTMLPage(String message) {
        return
                "<html>" +
                    "<head>" +
                        "<title>" +
                            "Info Page" +
                        "</title>" +
                    "</head>" +
                    "<body>" +
                        "<h3>" +
                        message +
                    "</h3>" +
                    "</body>" +
                "</html>";

    }
}
