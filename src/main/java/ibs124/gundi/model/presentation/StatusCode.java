package ibs124.gundi.model.presentation;

public class StatusCode {

    private static final int SUCCESS = 0;
    private static final int FAILURE = 1;
    private static final int UNDEFINED = -1;

    private final int code;

    public StatusCode(int code) {
        this.code = code;
    }

    public static StatusCode custom(int code) {
        return new StatusCode(code);
    }

    public static StatusCode success() {
        return new StatusCode(SUCCESS);
    }

    public static StatusCode failure() {
        return new StatusCode(FAILURE);
    }

    public static StatusCode undefined() {
        return new StatusCode(UNDEFINED);
    }

    public int getCode() {
        return code;
    }

}
