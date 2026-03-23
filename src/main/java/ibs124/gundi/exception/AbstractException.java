package ibs124.gundi.exception;

import ibs124.gundi.model.entity.AbstractEntity;

public abstract class AbstractException extends RuntimeException {

    private Class<? extends AbstractEntity> target;

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

    public Class<? extends AbstractEntity> getTarget() {
        return target;
    }

    public void setTarget(Class<? extends AbstractEntity> target) {
        this.target = target;
    }

}