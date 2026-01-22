package ibs124.gundi.constant;

public class Templates {

    // Core
    public static final String INDEX = "index";

    // Auth
    private static final String AUTH = "/auth";
    public static final String REGISTER = AUTH + "/register";
    public static final String REGISTER_SUCCESS = REGISTER + "-success";

    public static final String LOGIN = AUTH + "/login";

    // Emails

    private static final String EMAILS = "/emails";

    public static final String NEW_USER_VERIFICATION_EMAIL = EMAILS
            + "/new-user-verification";

    // Users
    private static final String USERS = "/users";
    public static final String USERS_SELF_PROFILE = USERS + "/self-profile";
    public static final String USERS_SELF_ACCOUNT = USERS + "/self-account";

    // Admins
    private static final String ADMINS = USERS + "/admins";
    public static final String ADMINS_INDEX = ADMINS + "/" + INDEX;

    // Root
    private static final String ROOT = USERS + "/root";
    public static final String ROOT_INDEX = ROOT + "/" + INDEX;

}
