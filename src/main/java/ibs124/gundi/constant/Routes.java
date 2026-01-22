package ibs124.gundi.constant;

public abstract class Routes {

    // Common
    public static final String CONVENTIONAL_INDEX = "/";
    public static final String CREATE = "/create";
    public static final String UPDATE = "/update";
    public static final String ERROR = "/error";
    public static final String SUCCESS = "/success";

    // Core
    public static final String INDEX = CONVENTIONAL_INDEX;

    // Auth
    public static final String AUTH = INDEX + "auth";
    public static final String REGISTER = AUTH + "/sign-up";
    public static final String REGISTER_SUCCESS = REGISTER + SUCCESS;
    public static final String LOGIN = AUTH + "/sign-in";
    public static final String LOGIN_ERROR = LOGIN + ERROR;
    public static final String LOGOUT = AUTH + "/sign-out";

    // Users
    public static final String USERS = INDEX + "users";
    public static final String USERS_SELF_PROFILE = USERS + "/me";
    public static final String USERS_SELF_ACCOUNT = USERS_SELF_PROFILE + "/account";

    // Admins
    public static final String ADMINS = INDEX + "admins";

    // Root
    public static final String ROOT = INDEX + "root";

}