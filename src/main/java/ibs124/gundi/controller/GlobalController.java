package ibs124.gundi.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import ibs124.gundi.constant.Routes;
import ibs124.gundi.constant.ThymeleafEnv;
import ibs124.gundi.utility.AppUtils;

@ControllerAdvice
class GlobalController {

    private final Map<String, String> routes = AppUtils
            .mapConstants(Routes.class);

    @ModelAttribute(ThymeleafEnv.ROUTES)
    public Map<String, String> globalRoutes() {
        return this.routes;
    }
}
