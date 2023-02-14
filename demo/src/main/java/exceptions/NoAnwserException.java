package exceptions;

public class NoAnwserException extends RuntimeException{
    public NoAnwserException() {
    }

    public NoAnwserException(String message) {
        super(message);
    }

    public NoAnwserException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoAnwserException(Throwable cause) {
        super(cause);
    }

    public NoAnwserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
