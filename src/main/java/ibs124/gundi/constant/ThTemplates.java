package ibs124.gundi.constant;

public class ThTemplates {

    public static final String INDEX = "index";

    private static final String AUTH = "/auth";
    public static final String REGISTER = AUTH + "/register";
    public static final String REGISTER_SUCCESS = REGISTER + "-success";

    public static final String LOGIN = AUTH + "/login";

    private static final String VERIFICATION_EMAILS = "/emails/verification";
    public static final String NEW_USER_VERIFICATION_EMAIL = VERIFICATION_EMAILS
            + "/new-user";

    private static final String USERS = "/users";
    public static final String USERS_SELF_PROFILE = USERS + "/profile";
    public static final String USERS_SELF_ACCOUNT = USERS + "/account";
    public static final String USERS_ADMINISTRATION = USERS + "/administration";

}
