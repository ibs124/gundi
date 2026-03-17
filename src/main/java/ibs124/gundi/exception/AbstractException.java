package ibs124.gundi.exception;

import ibs124.gundi.model.persistence.AbstractDomainModel;

public abstract class AbstractException extends RuntimeException {

    private Class<? extends AbstractDomainModel> target;

    public AbstractException() {
        super();
    }

    public AbstractException(String message) {
        super(message);
    }

    public AbstractException(Throwable cause) {
        super(cause);
    }

    public AbstractException(String message, Throwable cause) {
        super(message, cause);
    }

    public Class<? extends AbstractDomainModel> getTarget() {
        return target;
    }

    public void setTarget(Class<? extends AbstractDomainModel> target) {
        this.target = target;
    }

}