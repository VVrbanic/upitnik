package exceptions;

public class NoNewQuestionException extends RuntimeException{
    public NoNewQuestionException() {
    }

    public NoNewQuestionException(String message) {
        super(message);
    }

    public NoNewQuestionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoNewQuestionException(Throwable cause) {
        super(cause);
    }

    public NoNewQuestionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
