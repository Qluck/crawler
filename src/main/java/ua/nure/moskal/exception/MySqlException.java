package ua.nure.moskal.exception;

public class MySqlException extends RuntimeException {
    MySqlException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
