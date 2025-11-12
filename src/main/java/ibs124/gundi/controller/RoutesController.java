package ibs124.gundi.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import ibs124.gundi.config.thymeleaf.AttributeConfig;

@ControllerAdvice
public class RoutesController {

    @ModelAttribute(AttributeConfig.ROUTES)
    public Map<String, String> globalRoutes() {
        return AttributeConfig.ROUTES_MAP;
    }
}
