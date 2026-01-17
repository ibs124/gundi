package ibs124.gundi.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import ibs124.gundi.config.RouteConfig;
import ibs124.gundi.config.thymeleaf.AttributeConfig;
import ibs124.gundi.util.Application;

@ControllerAdvice
class RoutesController {

    private final Map<String, String> routes = Application
            .mapConstants(RouteConfig.class);

    @ModelAttribute(AttributeConfig.ROUTES)
    public Map<String, String> globalRoutes() {
        return this.routes;
    }
}
