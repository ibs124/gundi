package ibs124.gundi.constant;

public abstract class Routes {

    public static final String CONVENTIONAL_INDEX = "/";
    public static final String CREATE = CONVENTIONAL_INDEX + Env.CREATE;
    public static final String UPDATE = CONVENTIONAL_INDEX + Env.UPDATE;
    public static final String ERROR = CONVENTIONAL_INDEX + Env.ERROR;
    public static final String SUCCESS = CONVENTIONAL_INDEX + Env.SUCCESS;
    public static final String SEND = CONVENTIONAL_INDEX + "send";

    public static final String INDEX = CONVENTIONAL_INDEX;

    public static final String AUTH = INDEX + "auth";
    public static final String REGISTER = AUTH + "/sign-up";
    public static final String REGISTER_SUCCESS = REGISTER + SUCCESS;
    public static final String LOGIN = AUTH + "/sign-in";
    public static final String LOGIN_ERROR = LOGIN + ERROR;
    public static final String LOGOUT = AUTH + "/sign-out";
    public static final String VERIFICATION = AUTH + "/verification";

    public static final String USERS = INDEX + "users";
    public static final String USERS_SELF_PROFILE = USERS + "/me";
    public static final String USERS_SELF_ACCOUNT = USERS_SELF_PROFILE + "/account";

    public static final String ADMINS = INDEX + "admins";

    public static final String ROOT = INDEX + "root";

}