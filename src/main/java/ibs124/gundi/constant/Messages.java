package ibs124.gundi.constant;

public abstract class Messages {

    private static final String NOT_FOUND = ".not_found}";
    private static final String FORMAT_ERROR = ".format_error}";
    private static final String TAKEN = ".taken}";
    private static final String REQUIRED = ".required}";

    public static final String BLANK_FIELD = "{blank_field}";

    public static final String USER_NOT_FOUND = "{user" + NOT_FOUND;

    private static final String EMAIL = "{" + Env.EMAIL;
    public static final String EMAIL_FORMAT_ERROR = EMAIL + FORMAT_ERROR;
    public static final String EMAIL_TAKEN = EMAIL + TAKEN;
    public static final String EMAIL_REQUIRED = EMAIL + REQUIRED;

    public static final String PASSWORD_FORMAT_ERROR = "{password" + FORMAT_ERROR;

    private static final String USERNAME = "{" + Env.USERNAME;
    public static final String USERNAME_FORMAT_ERROR = USERNAME + FORMAT_ERROR;
    public static final String USERNAME_TAKEN = USERNAME + TAKEN;

    public static final String FULL_NAME_FORMAT_ERROR = "{full_name" + FORMAT_ERROR;
}
