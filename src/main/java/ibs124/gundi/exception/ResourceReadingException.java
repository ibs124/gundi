package ibs124.gundi.exception;

public class ResourceReadingException extends RuntimeException {

    public ResourceReadingException() {
    }

    public ResourceReadingException(String message) {
        super(message);
    }

    public ResourceReadingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceReadingException(Throwable cause) {
        super(cause);
    }

}