package ibs124.gundi.config.message;

public abstract class UserMessagesConfig {

    private static final String BLANK = "must not be blank!";
    private static final String EMAIL = "Email ";
    private static final String PASSWORD = "Password ";
    private static final String FULL_NAME = "Full name ";
    private static final String USERNAME = "Username ";

    public static final String EMAIL_BLANK = EMAIL + BLANK;

    public static final String EMAIL_ERROR = "Invalid email address!";

    public static final String PASSWORD_BLANK = PASSWORD + BLANK;

    public static final String PASSWORD_ERROR = """
            Password must contain at least:
            - Five characters
            - One uppercase letter (A–Z)
            - One lowercase letter (a–z)
            - One digit (0–9)
            - One special character (e.g. !, @, #, $, %)
                                    """;;

    public static final String USERNAME_BLANK = USERNAME + BLANK;

    public static final String USERNAME_ERROR = """
            Username must be between three and twenty characters long.
            Allowed characters:
                - Lowercased letters (a-z)
                - Digits (0-9)
                - Underscores (_)
                """;

    public static final String FULL_NAME_BLANK = FULL_NAME + BLANK;

    public static final String FULL_NAME_ERROR = """
            Full name msut be between one and four words.
            Each word must be between three sixteen eharacters long.
            Only lowercased and uppercased letters allowed.
                    """;
}
