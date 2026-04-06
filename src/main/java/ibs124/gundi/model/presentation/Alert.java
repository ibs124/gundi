package ibs124.gundi.model.presentation;

public record Alert(String message, String cssClass) {

    private static final String BASE = "alert alert";
    public static final String SUCCESS = BASE + "__success";
    public static final String DANGER = BASE + "__danger";
    public static final String INFO = BASE + "__info";
    public static final String WARNING = BASE + "__warning";

    public static final Alert success(String message) {
        return new Alert(message, SUCCESS);
    }

    public static final Alert danger(String message) {
        return new Alert(message, DANGER);
    }

    public static final Alert info(String message) {
        return new Alert(message, INFO);
    }

    public static final Alert warning(String message) {
        return new Alert(message, WARNING);
    }

    public String getMessage() {
        return this.message();
    }

    public String getCssClass() {
        return this.cssClass();
    }

    public String getPlainMessageKey() {
        if (message == null) {
            return null;
        }

        if (message.startsWith("{") && message.endsWith("}")) {
            return message.substring(1, message.length() - 1);
        }

        return message;
    }

}