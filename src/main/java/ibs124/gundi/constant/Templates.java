package ibs124.gundi.constant;

public class Templates {

    public static final String INDEX = "index";

    private static final String AUTH = "/auth";
    public static final String AUTH_REGISTER = AUTH + "/register";
    public static final String AUTH_LOGIN = AUTH + "/login";
    public static final String AUTH_VERIFICATION = AUTH + "/verification";
    public static final String AUTH_VERIFICATION_EMAIL = AUTH_VERIFICATION + "-email";
    public static final String AUTH_PASSWORD_RESET = AUTH + "/password-reset";

    public static final String HOME = "home";

    private static final String USERS = "/users";
    public static final String USERS_SELF_PROFILE = USERS + "/profile";
    public static final String USERS_SELF_ACCOUNT = USERS + "/account";
    public static final String USERS_ADMINISTRATION = USERS + "/administration";

}
