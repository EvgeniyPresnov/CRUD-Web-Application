package ru.home.webapp.servlets;

import org.apache.log4j.Logger;
import ru.home.webapp.model.dao.DAOException;
import ru.home.webapp.model.dao.IUserDAO;
import ru.home.webapp.model.dao.UserDAO;
import ru.home.webapp.utils.ConnectionDBException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * This class implements the logic for deleting a user from the database
 *
 * @author Evgeniy Presnov
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(LogoutServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IUserDAO userDAO = new UserDAO();
        String user = (String) req.getSession().getAttribute("loginedUser");
        req.setAttribute("deletedUser", user);

        try {
            userDAO.deleteUser(user);
        } catch (DAOException | ConnectionDBException e) {
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("User '" + user + "' is logged out");
        req.getRequestDispatcher("/WEB-INF/view/logout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);

    }
}
