package ibs124.gundi.exception;

public class ResourceUpdatingException extends RuntimeException {

    public ResourceUpdatingException() {
    }

    public ResourceUpdatingException(String message) {
        super(message);
    }

    public ResourceUpdatingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceUpdatingException(Throwable cause) {
        super(cause);
    }

}