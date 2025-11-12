package ibs124.gundi.config.thymeleaf;

import java.util.Map;

import ibs124.gundi.config.RoutesConfig;

public abstract class AttributesConfig {

    public static final String SUCCESS = "success";

    public static final String VIEW_MODEL = "viewModel";

    public static final String BINDING_MODEL = "bindingModel";

    public static final String BINDING_RESULT = "org.springframework.validation.BindingResult."
            + BINDING_MODEL;

    public static final String ROUTES = "routes";

    public static final Map<String, String> ROUTES_MAP = Map.of(
            "index", RoutesConfig.INDEX,
            "register", RoutesConfig.REGISTER,
            "register.success", RoutesConfig.REGISTER_SUCCESS,
            "login", RoutesConfig.LOGIN,
            "logout", RoutesConfig.LOGOUT);
}
