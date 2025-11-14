package ibs124.gundi.config.thymeleaf;

public class TemplateConfig {

    public static final String INDEX = "index";

    private static final String AUTH = "/auth";
    public static final String REGISTER = AUTH + "/register";
    public static final String REGISTER_SUCCESS = REGISTER + "-success";
    public static final String LOGIN = AUTH + "/login";

    private static final String USERS = "/users";
    public static final String USERS_ME = USERS + "/me";

}
