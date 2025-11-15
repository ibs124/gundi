package ibs124.gundi.config.message;

public abstract class ErrorMessageConfig {

    public static final String BLANK_FIELD = "This field is required.";

    public static final String EMAIL = "Invalid email address.";

    public static final String PASSWORD = "Password must be at least 6 characters long.";

    public static final String USERNAME = """
            Username must be between 3 and 20 characters long,
            it can only use letters, numbers and underscores.
            """;

    public static final String USERNAME_TAKEN = "Username already taken.";

    public static final String FULL_NAME_ERROR = "";
}
