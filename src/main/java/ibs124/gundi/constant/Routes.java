package ibs124.gundi.constant;

public abstract class Routes {

    public static final String CONVENTIONAL_INDEX = "/";
    public static final String CREATE = CONVENTIONAL_INDEX + Env.CREATE;
    public static final String UPDATE = CONVENTIONAL_INDEX + Env.UPDATE;
    public static final String ERROR = CONVENTIONAL_INDEX + Env.ERROR;
    public static final String SUCCESS = CONVENTIONAL_INDEX + Env.SUCCESS;
    public static final String SEND = CONVENTIONAL_INDEX + "send";
    public static final String SUBMIT = CONVENTIONAL_INDEX + "submit";
    public static final String VAR_TOKEN = "token";

    public static final String INDEX = CONVENTIONAL_INDEX;

    public static final String AUTH = INDEX + "auth";
    public static final String REGISTER = AUTH + "/sign-up";
    public static final String REGISTER_SUCCESS = REGISTER + SUCCESS;
    public static final String LOGIN = AUTH + "/sign-in";
    public static final String LOGIN_ERROR = LOGIN + ERROR;
    public static final String LOGOUT = AUTH + "/sign-out";
    public static final String VERIFICATION = AUTH + "/verification";
    public static final String VERIFICATION_SEND = VERIFICATION + SEND;
    public static final String VERIFICATION_SUBMIT = VERIFICATION + SUBMIT;
    public static final String VERIFICATION_SUCCESS = VERIFICATION + SUCCESS;
    public static final String VERIFICATION_ERROR = VERIFICATION + ERROR;
    public static final String PASSWORD_RESET = AUTH + "/password-reset";
    public static final String PASSWORD_RESSET_SUBMIT = PASSWORD_RESET + SUBMIT;
    public static final String PASSWORD_RESET_SUCCESS = PASSWORD_RESET + SUCCESS;
    public static final String PASSWORD_RESET_ERROR = PASSWORD_RESET + ERROR;

    public static final String HOME = INDEX;

    public static final String USERS = INDEX + "users";
    public static final String USERS_SELF_PROFILE = USERS + "/me";
    public static final String USERS_SELF_ACCOUNT = USERS_SELF_PROFILE + "/account";
    public static final String ADMINS = INDEX + "admins";
    public static final String ROOT = INDEX + "root";

}