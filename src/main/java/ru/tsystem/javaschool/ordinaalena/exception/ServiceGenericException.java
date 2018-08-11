package ru.tsystem.javaschool.ordinaalena.exception;

public class ServiceGenericException extends DaoGenericException {
    public ServiceGenericException(String message) {
        super(message);
    }

    public ServiceGenericException(String message, Throwable cause) {
        super(message, cause);
    }

}
