package ibs124.gundi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ibs124.gundi.constant.Routes;
import ibs124.gundi.constant.ThTemplates;

@Controller
public class IndexController {

    @GetMapping(Routes.INDEX)
    public String index() {
        return ThTemplates.INDEX;
    }

}
