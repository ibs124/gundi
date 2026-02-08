package ibs124.gundi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ibs124.gundi.constant.Routes;
import ibs124.gundi.constant.Templates;

@Controller
public class IndexController {

    @GetMapping(Routes.INDEX)
    public String index() {
        return Templates.INDEX;
    }

}
