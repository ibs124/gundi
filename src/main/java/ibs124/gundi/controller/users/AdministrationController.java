package ibs124.gundi.controller.users;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ibs124.gundi.constant.Routes;
import ibs124.gundi.constant.ThTemplates;

@Controller
class AdministrationController {

    @GetMapping(Routes.ADMINS)
    public String getMethodName() {
        return ThTemplates.USERS_ADMINISTRATION;
    }

}