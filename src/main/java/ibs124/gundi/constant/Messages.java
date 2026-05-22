package ibs124.gundi.constant;

public abstract class Messages {

    private static final String SUCCESS = ".success}";
    private static final String NOT_FOUND = ".not_found}";
    private static final String ERROR = ".error}";
    private static final String TAKEN = ".taken}";
    private static final String REQUIRED = ".required}";
    private static final String SENT = ".sent}";

    public static final String COMMON_GREETING = "{common.greeting}";

    public static final String BLANK_FIELD = "{blank_field}";

    private static final String USERNAME = "{" + Env.USERNAME;
    public static final String USER_NOT_FOUND = "{user" + NOT_FOUND;
    public static final String USERNAME_ERROR = USERNAME + ERROR;
    public static final String USERNAME_TAKEN = USERNAME + TAKEN;
    public static final String PASSWORD_ERROR = "{password" + ERROR;
    public static final String FULL_NAME_ERROR = "{full_name" + ERROR;

    private static final String EMAIL = "{" + Env.EMAIL;
    public static final String EMAIL_ERROR = EMAIL + ERROR;
    public static final String EMAIL_TAKEN = EMAIL + TAKEN;
    public static final String EMAIL_REQUIRED = EMAIL + REQUIRED;

    public static final String REGISTER_SUCCESS = "{register" + SUCCESS;
    public static final String LOGIN_ERROR = "{login" + ERROR;

    private static final String VERIFICATION = "{verification";
    public static final String VERIFICATION_SUCCESS = VERIFICATION + SUCCESS;
    public static final String VERIFICATION_ERROR = VERIFICATION + ERROR;
    public static final String VERIFICATION_SENT = VERIFICATION + SENT;

    public static final String PASSWORD_RESET = "{password_reset";
    public static final String PASSWORD_RESET_SUCCESS = PASSWORD_RESET + SUCCESS;
    public static final String PASSWORD_RESET_ERROR = PASSWORD_RESET + ERROR;
    public static final String PASSWORD_RESET_SENT = PASSWORD_RESET + SENT;

}
