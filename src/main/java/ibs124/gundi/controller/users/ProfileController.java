package ibs124.gundi.controller.users;

import org.springframework.stereotype.Controller;

import ibs124.gundi.constant.Routes;
import ibs124.gundi.constant.Templates;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
class ProfileController {

    @GetMapping(Routes.USERS_SELF_PROFILE)
    public String index() {

        return Templates.USERS_SELF_PROFILE;
    }

}
