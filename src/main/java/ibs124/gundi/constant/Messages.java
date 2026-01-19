package ibs124.gundi.constant;

public abstract class Messages {

    public static final String BLANK_FIELD = "This field is required.";

    public static final String USER_NOT_FOUND = "User not found";

    public static final String EMAIL_FORMAT_ERROR = "Invalid email address.";

    public static final String EMAIL_TAKEN = "Email already taken.";

    public static final String EMAIL_REQUIRED = "Email required.";

    public static final String PASSWORD_FORMAT_ERROR = "Password must be at least 6 characters long.";

    public static final String USERNAME_FORMAT_ERROR = """
            Username must be between 3 and 20 characters long,
            it can only use letters, numbers and underscores.
            """;

    public static final String USERNAME_TAKEN = "Username already taken.";

    public static final String FULL_NAME_FORMAT_ERROR = "Invalid full name.";
}
