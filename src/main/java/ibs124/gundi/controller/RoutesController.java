package ibs124.gundi.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import ibs124.gundi.config.thymeleaf.AttributesConfig;

@ControllerAdvice
public class RoutesController {

    @ModelAttribute(AttributesConfig.ROUTES)
    public Map<String, String> globalRoutes() {
        return AttributesConfig.ROUTES_MAP;
    }
}
