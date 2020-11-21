package ru.home.webapp.logging;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * This class implements a simple logging method
 *
 * @author Evgeniy Presnov
 */
public class LogHandler implements Serializable {
    private static final long serialVersionUID = 1L;
    private Logger logger;

    public LogHandler(Object className) {
        logger = Logger.getLogger(className.getClass());
    }

    public void logInfo(final String message) {
        logger.info(message);
    }

    public void logWarning(final String message) { logger.warn(message);}

    public void logError(final Throwable exception) {
        logger.error(exception);
    }

    private void writeObject(final ObjectOutputStream stream)
            throws IOException {
        stream.defaultWriteObject();
    }

    private void readObject(final ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
    }

}