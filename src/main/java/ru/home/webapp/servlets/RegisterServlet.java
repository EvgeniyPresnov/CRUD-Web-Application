package ru.home.webapp.servlets;


import ru.home.webapp.logging.LogHandler;
import ru.home.webapp.model.dao.IUserDAO;
import ru.home.webapp.model.dao.UserDAO;
import ru.home.webapp.model.entities.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * This class implements the logic of user registration in the database
 *
 * @author Evgeniy Presnov
 */
@WebServlet("/registration")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Display the page of registration
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/registration.jsp").forward(req, resp);
    }

    /**
     * After the user has filled information about themselves and clicked on button.
     * This method will be executed
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        IUserDAO userDAO = new UserDAO();
        User user = new User();
        LogHandler logHandler = new LogHandler(this);

        String userName = req.getParameter("name");
        String password = req.getParameter("password");
        String submitType = req.getParameter("submit");
        String repeatPassword = req.getParameter("repeatPassword");

        /**
         * Checking for filling in the fields on the form.
         * If all fields are not empty
         */
        if (submitType.equals("register") && !userName.equals("") && !password.equals("") && !repeatPassword.equals("")) {
            /**
             * Checking whether the password matches and the repeated password.
             */
            if (password.equals(repeatPassword)) {
                user.setName(userName);
                user.setPassword(password);
                userDAO.addUser(user);

                HttpSession session = req.getSession();
                session.setAttribute("loginedUser", user.getName());

                logHandler.logInfo("User " + user.getName() + " was registered");
                /**
                * User will be added to the database
                */
                req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
            }
            /**
             * If the fields password and repeated password are not equal. User will see the warning message
             */
            else {
                logHandler.logWarning(MessageBox.NOT_EQUALS_PASSWORDS.message);

                PrintWriter out = resp.getWriter();
                out.println(getHTMLPage(MessageBox.NOT_EQUALS_PASSWORDS_INFO_MSG_FOR_USER.message));
                out.close();
            }
        }
        /**
         *  User will see the warning message
         */
        else if (submitType.equals("register") || userName.equals("") || password.equals("") || repeatPassword.equals("")){
            logHandler.logWarning(MessageBox.FIELDS_EMPTY.message);

            PrintWriter out = resp.getWriter();
            out.println(getHTMLPage(MessageBox.FIELDS_EMPTY_INFO_MSG_FOR_USER.message));
            out.close();

            req.getRequestDispatcher("/WEB-INF/view/registration.jsp").forward(req, resp);
        }

    }

    private enum MessageBox {
        FIELDS_EMPTY("The filed User Name or Password or Re-Password is empty"),
        NOT_EQUALS_PASSWORDS("Password and Re-Password are not equal"),

        FIELDS_EMPTY_INFO_MSG_FOR_USER("The filed User Name or Password or Re-Password is empty. Please, try it again"),
        NOT_EQUALS_PASSWORDS_INFO_MSG_FOR_USER("Password and Re-Password are not equal. Please, try it again");

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
