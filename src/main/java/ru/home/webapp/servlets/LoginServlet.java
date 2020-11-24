package ru.home.webapp.servlets;

import ru.home.webapp.logging.LogHandler;
import ru.home.webapp.model.dao.DAOException;
import ru.home.webapp.model.dao.IUserDAO;
import ru.home.webapp.model.dao.UserDAO;
import ru.home.webapp.model.entities.User;
import ru.home.webapp.utils.ConnectionDBException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Class implements the logic of logging in to the application
 *
 * @author Evgeniy Presnov
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /*
     Display the page of logging in
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
    }

    /*
     After the user has filled information about themselves and clicked on button.
     This method will be executed
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        String submitType = req.getParameter("submit");
        String loginedUser = (String) req.getSession().getAttribute("loginedUser");

        LogHandler logHandler = new LogHandler(this);

         /*
         Checking for filling in the fields on the form.

         If all fields are not empty
         */
        if (submitType.equals("login") && !userName.equals("") && !password.equals("")) {
            IUserDAO userDAO = new UserDAO();
            User user = null;
            try {
                user = userDAO.getUser(userName, password);
            } catch (DAOException e) {
                e.printStackTrace();
            } catch (ConnectionDBException e) {
                e.printStackTrace();
            }
             /*
             The search for a user in the database
             */
            if ((user.getName() != null && user.getPassword() != null) &&
                    user.getName().equals(userName) && user.getPassword().equals(password)) {
                logHandler.logInfo("User '" + loginedUser + "' in logged in");

                resp.sendRedirect(req.getContextPath() + "/home");
            }
            /*
             If the user is not in the database when logging in, the user will see
             the warning message
             */
            else {
                logHandler.logWarning(MessageBox.USER_NOT_FOUND.message);

                PrintWriter out = resp.getWriter();
                out.println(getHTMLPage(MessageBox.USER_NOT_FOUND_INFO_MSG_FOR_USER.message));
                out.close();

                req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
            }
        }
        /*
         If at least one field is empty, user will see the warning message
         */
        else if (submitType.equals("login") && userName.equals("") || password.equals("")) {
            logHandler.logWarning(MessageBox.FIELDS_EMPTY.message);

            PrintWriter out = resp.getWriter();
            out.println(getHTMLPage(MessageBox.FIELDS_EMPTY_INFO_MSG_FOR_USER.message));
            out.close();
        }
    }

    private enum MessageBox {
        USER_NOT_FOUND("The user does not find in data base"),
        FIELDS_EMPTY("The field User Name or Password is empty"),
        FIELDS_EMPTY_INFO_MSG_FOR_USER("The field User Name or Password is empty. Please, try it again"),
        USER_NOT_FOUND_INFO_MSG_FOR_USER("The user does not find in data base.Please, click on Register if you don't do it");

        String message;

        MessageBox (String message) {
            this.message = message;
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
