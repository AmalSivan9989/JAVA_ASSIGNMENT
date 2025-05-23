package com.hexaware.exception;

public class DbConnectionException extends Exception {
    public DbConnectionException() {
    }

    public DbConnectionException(Throwable cause) {
        super(cause);
    }

    public DbConnectionException(String message) {
        super(message);
    }

    public DbConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public DbConnectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
