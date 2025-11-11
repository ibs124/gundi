package ibs124.gundi.exception;

public class ResourceDeletingException extends RuntimeException {

    public ResourceDeletingException() {
    }

    public ResourceDeletingException(String message) {
        super(message);
    }

    public ResourceDeletingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceDeletingException(Throwable cause) {
        super(cause);
    }

}