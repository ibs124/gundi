package ibs124.gundi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ibs124.gundi.config.RoutesConfig;
import ibs124.gundi.config.thymeleaf.TemplatesConfig;

@Controller
public class HomeController {

    @GetMapping(RoutesConfig.INDEX)
    public String getIndex() {
        return TemplatesConfig.INDEX;
    }

}
