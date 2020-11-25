package ru.home.webapp.servlets.listeners;

import org.apache.log4j.Logger;
import ru.home.webapp.utils.ConnectionDB;
import ru.home.webapp.utils.ConnectionDBException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * This class is used for initialize a database connection pool
 * before the web application is started
 *
 * @author Evgeniy Presnov
 */
@WebListener
public final class ConnectionDBListener implements ServletContextListener {
    private static Logger logger = Logger.getLogger(ConnectionDBListener.class.getName());

    /**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            ConnectionDB.getInstance();
        } catch (ConnectionDBException e) {
            e.printStackTrace();
            logger.error("There is no database connection: ", e);
        }
        logger.info("Database connection initialized for Application");
    }

    /**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

        try {
            ConnectionDB.getInstance().closeConnection();
        } catch (ConnectionDBException e) {
            e.printStackTrace();
            logger.error("The connection to database was not close: ", e);
        }
        logger.info("Database connection closed for Application");
    }
}
