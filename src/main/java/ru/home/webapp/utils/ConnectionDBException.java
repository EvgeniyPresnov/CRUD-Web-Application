package ru.home.webapp.utils;

/**
 * This class implements the exception logic on Application layer
 *
 * @author Evgeniy Presnov
 */
public class ConnectionDBException extends Exception {
    private static final long serialVersionUID = 1L;

    public ConnectionDBException() {
        super();
    }

    public ConnectionDBException(String message) {
        super(message);
    }

    public ConnectionDBException(String message, Throwable cause) {
        super(message, cause);
    }
}
