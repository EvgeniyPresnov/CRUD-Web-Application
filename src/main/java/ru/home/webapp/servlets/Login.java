package ru.home.webapp.servlets;

import org.apache.log4j.Logger;
import ru.home.webapp.dao.DAOException;
import ru.home.webapp.dao.IUserDAO;
import ru.home.webapp.dao.UserDAO;
import ru.home.webapp.domain.User;
import ru.home.webapp.utils.ConnectionDBException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class implements the logic of logging in to the application
 *
 * @author Evgeniy Presnov
 */
@WebServlet("/login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(Login.class.getName());

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

         /*
         Checking for filling in the fields on the form.

         If all fields are not empty
         */
        if (submitType.equals("Login") && !userName.equals("") && !password.equals("")) {
            IUserDAO userDAO = new UserDAO();
            User user = new User();
            try {
                user = userDAO.getUser(userName, password);
            } catch (DAOException | ConnectionDBException e) {
                e.printStackTrace();
                logger.error(e);
            }
             /*
             The search for a user in the database
             */
            if (user.getName() != null && user.getPassword() != null &&
                    user.getName().equals(userName) && user.getPassword().equals(password)) {
                logger.info("User '" + loginedUser + "' in logged in");
                resp.sendRedirect(req.getContextPath() + "/home");
            }
            /*
             If the user is not in the database when logging in, the user will see
             the warning message
             */
            else {
                logger.warn("The user does not find in data base");

                PrintWriter out = resp.getWriter();
                out.println(getHTMLPage("The user does not find in data base. " +
                        "Please, click on Register if you don't do it"));
                out.close();

                req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
            }
        }
        /*
         If at least one field is empty, user will see the warning message
         */
        else if (submitType.equals("login") && userName.equals("") || password.equals("")) {
            logger.warn("The field User Name or Password is empty");

            PrintWriter out = resp.getWriter();
            out.println(getHTMLPage("The field User Name or Password is empty." +
                    " Please, try it again"));
            out.close();
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
