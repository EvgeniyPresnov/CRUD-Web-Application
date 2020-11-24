package ru.home.webapp.servlets;

import ru.home.webapp.logging.LogHandler;
import ru.home.webapp.model.dao.DAOException;
import ru.home.webapp.model.dao.IUserDAO;
import ru.home.webapp.model.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * This class implements the logic for deleting a user from the database
 *
 * @author Evgeniy Presnov
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IUserDAO userDAO = new UserDAO();
        LogHandler logHandler = new LogHandler(this);

        String user = (String) req.getSession().getAttribute("loginedUser");
        req.setAttribute("deletedUser", user);

        try {
            userDAO.deleteUser(user);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        logHandler.logInfo("User '" + user + "' is logged out");
        req.getRequestDispatcher("/WEB-INF/view/logout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);

    }
}
