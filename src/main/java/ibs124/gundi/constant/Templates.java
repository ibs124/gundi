package ibs124.gundi.constant;

public class Templates {

    private static final String EMAIL_SUFFIX = "-email";

    public static final String INDEX = "index";

    private static final String AUTH = "/auth";
    public static final String REGISTER = AUTH + "/register";
    public static final String LOGIN = AUTH + "/login";
    public static final String VERIFICATION = AUTH + "/verification";
    public static final String VERIFICATION_EMAIL = VERIFICATION + EMAIL_SUFFIX;
    public static final String PASSWORD_RESET = AUTH + "/password-reset";
    public static final String PASSWORD_RESET_EMAIL = PASSWORD_RESET + EMAIL_SUFFIX;
    public static final String PASSWORD_RESET_SUBMIT = PASSWORD_RESET + "-submit";

    public static final String HOME = "home";

    private static final String USERS = "/users";
    public static final String USERS_SELF_PROFILE = USERS + "/profile";
    public static final String USERS_SELF_ACCOUNT = USERS + "/account";
    public static final String USERS_ADMINISTRATION = USERS + "/administration";

}
