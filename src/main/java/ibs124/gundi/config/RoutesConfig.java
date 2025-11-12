package ibs124.gundi.config;

public abstract class RoutesConfig {

    public static final String CREATE = "/create";

    public static final String UPDATE = "/update";

    public static final String ERROR = "/error";

    public static final String SUCCESS = "/success";

    public static final String INDEX = "/";

    public static final String USERS = "/users";

    public static final String REGISTER = USERS + "/sign-up";

    public static final String REGISTER_SUCCESS = REGISTER + SUCCESS;

    public static final String LOGIN = USERS + "/sign-in";

    public static final String LOGOUT = USERS + "/sign-out";

}
