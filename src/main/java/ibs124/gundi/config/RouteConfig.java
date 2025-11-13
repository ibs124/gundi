package ibs124.gundi.config;

public abstract class RouteConfig {

    // !!! Workflow: 1. Define constant 2. Add it to one of the groups

    public static final String CREATE = "/create";

    public static final String UPDATE = "/update";

    public static final String ERROR = "/error";

    public static final String SUCCESS = "/success";

    public static final String INDEX = "/";

    public static final String AUTH = INDEX + "auth";

    public static final String REGISTER = AUTH + "/sign-up";

    public static final String REGISTER_SUCCESS = REGISTER + SUCCESS;

    public static final String LOGIN = AUTH + "/sign-in";

    public static final String LOGIN_ERROR = LOGIN + ERROR;

    public static final String LOGOUT = AUTH + "/sign-out";

    public static final String USERS = INDEX + "users";

    static final String[] GUEST_ROUTES_GROUP = {
            INDEX, REGISTER, REGISTER_SUCCESS, LOGIN, LOGIN_ERROR, LOGOUT,
    };

    static final String[] USER_ROUTES_GROUP = {
    };

    static final String[] ADMIN_ROUTES_GROUP = {
    };

    static final String[] ROOT_ROUTES_GROUP = {
    };
}