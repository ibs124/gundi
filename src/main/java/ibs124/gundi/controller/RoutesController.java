package ibs124.gundi.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import ibs124.gundi.constant.Routes;
import ibs124.gundi.constant.ThymeleafAttributes;
import ibs124.gundi.utility.Application;

@ControllerAdvice
class RoutesController {

    private final Map<String, String> routes = Application
            .mapConstants(Routes.class);

    @ModelAttribute(ThymeleafAttributes.ROUTES)
    public Map<String, String> globalRoutes() {
        return this.routes;
    }
}
