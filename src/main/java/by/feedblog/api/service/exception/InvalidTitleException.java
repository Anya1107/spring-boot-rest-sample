package by.feedblog.api.service.exception;

public class InvalidTitleException extends RuntimeException{
    private String title;
    private String method;

    public InvalidTitleException() {
        super();
    }

    public InvalidTitleException(String message, String title, String method) {
        super(message);
        this.title = title;
        this.method = method;
    }

    public InvalidTitleException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidTitleException(Throwable cause) {
        super(cause);
    }

    protected InvalidTitleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getTitle() {
        return title;
    }

    public String getMethod() {
        return method;
    }
}
