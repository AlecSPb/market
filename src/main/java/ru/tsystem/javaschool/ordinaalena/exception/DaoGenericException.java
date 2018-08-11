package ru.tsystem.javaschool.ordinaalena.exception;

/**
 * An exception to be thrown when a database-related issue occurs
 */
public class DaoGenericException extends RuntimeException {
    public DaoGenericException(String message) {
        super(message);
    }

    public DaoGenericException(String message, Throwable cause) {
        super(message, cause);
    }
}
