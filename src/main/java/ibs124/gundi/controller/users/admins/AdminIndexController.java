package ibs124.gundi.controller.users.admins;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ibs124.gundi.constant.Routes;
import ibs124.gundi.constant.Templates;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping(Routes.ADMINS)
public class AdminIndexController {

    @GetMapping
    public String index() {
        return Templates.ADMINS_INDEX;
    }

}
