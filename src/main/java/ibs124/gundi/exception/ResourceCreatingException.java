package ibs124.gundi.exception;

public class ResourceCreatingException extends AbstractException {

    public ResourceCreatingException() {
    }

    public ResourceCreatingException(String message) {
        super(message);
    }

    public ResourceCreatingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceCreatingException(Throwable cause) {
        super(cause);
    }

}