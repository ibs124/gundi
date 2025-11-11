package ibs124.gundi.config.thymeleaf;

import java.util.Map;

import ibs124.gundi.config.Routes;

public abstract class Attributes {

    public static final String SUCCESS = "success";

    public static final String VIEW_MODEL = "viewModel";

    public static final String BINDING_MODEL = "bindingModel";

    public static final String BINDING_RESULT = "org.springframework.validation.BindingResult."
            + BINDING_MODEL;

    public static final String ROUTES = "routes";

    public static final Map<String, String> ROUTES_MAP = Map.of(
            "index", Routes.INDEX,
            "register", Routes.REGISTER,
            "register.success", Routes.REGISTER_SUCCESS,
            "login", Routes.LOGIN,
            "logout", Routes.LOGOUT);
}
