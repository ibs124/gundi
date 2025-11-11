package ibs124.gundi.constant.message;

public abstract class UserMessages {

    private static final String BLANK = "must not be blank!";
    private static final String EMAIL = "Email ";
    private static final String PASSWORD = "Password ";
    private static final String USERNAME = "Username ";

    public static final String EMAIL_BLANK = EMAIL + BLANK;

    public static final String EMAIL_ERROR = "Invalid email address!";

    public static final String PASSWORD_BLANK = PASSWORD + BLANK;
    public static final String PASSWORD_ERROR = PASSWORD + "must be between 3 and 20 characters long.";

    public static final String USERNAME_BLANK = USERNAME + BLANK;

    public static final String USERNAME_ERROR = """
            Username must be between 3 and 20 characters long!
            Allowed characters:
                - Lowercased letters (a - z)
                - Digits (0 - 9)
                - Underscores (_)
                """;

}
