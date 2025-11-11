package ibs124.gundi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ibs124.gundi.config.Routes;
import ibs124.gundi.config.thymeleaf.Templates;

@Controller
public class HomeController {

    @GetMapping(Routes.INDEX)
    public String getIndex() {
        return Templates.INDEX;
    }

}
