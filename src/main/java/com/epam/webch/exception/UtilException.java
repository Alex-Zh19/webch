package com.epam.webch.exception;

public class UtilException extends Exception {

    public UtilException() {
    }

    public UtilException(String message) {
        super(message);
    }

    public UtilException(Throwable cause) {
        super(cause);
    }

    public UtilException(String message, Throwable cause) {
        super(message, cause);
    }
}