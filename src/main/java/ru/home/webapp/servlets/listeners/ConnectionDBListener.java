package ru.home.webapp.servlets.listeners;

import ru.home.webapp.logging.LogHandler;
import ru.home.webapp.utils.ConnectionDB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;

/**
 * This class is used for initialize a database connection pool
 * before the web application is started
 *
 * @author Evgeniy Presnov
 */
@WebListener
public final class ConnectionDBListener implements ServletContextListener {
    private LogHandler logHandler = new LogHandler(this);
    private static final String INIT_CONNECTION = "Database connection initialized for Application";
    private static final String CLOSE_CONNECTION = "Database connection closed for Application";

    /**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            ConnectionDB.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
            logHandler.logError(e.fillInStackTrace());
        }
        logHandler.logInfo(INIT_CONNECTION);
    }

    /**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            ConnectionDB.getInstance().closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            logHandler.logError(e.fillInStackTrace());
        }
        logHandler.logInfo(CLOSE_CONNECTION);
    }
}
