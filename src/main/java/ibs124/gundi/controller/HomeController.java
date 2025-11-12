package ibs124.gundi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ibs124.gundi.config.RouteConfig;
import ibs124.gundi.config.thymeleaf.TemplateConfig;

@Controller
public class HomeController {

    @GetMapping(RouteConfig.INDEX)
    public String getIndex() {
        return TemplateConfig.INDEX;
    }

}
