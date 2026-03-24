package ibs124.gundi.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ibs124.gundi.constant.Routes;
import ibs124.gundi.constant.Templates;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping(Routes.USERS_SELF_ACCOUNT)
public class AccountController {

    @GetMapping
    public String index() {

        return Templates.USERS_SELF_ACCOUNT;
    }

}
