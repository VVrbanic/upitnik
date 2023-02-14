package exceptions;

public class NoQuestionsFoundException extends Exception{
    public NoQuestionsFoundException() {
    }

    public NoQuestionsFoundException(String message) {
        super(message);
    }

    public NoQuestionsFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoQuestionsFoundException(Throwable cause) {
        super(cause);
    }

    public NoQuestionsFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
