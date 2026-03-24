package ibs124.gundi.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ibs124.gundi.constant.Routes;
import ibs124.gundi.constant.Templates;
import ibs124.gundi.constant.ThymeleafEnv;

@Controller
@RequestMapping(Routes.AUTH_LOGIN)
class LoginController {

    @GetMapping
    public String getMethodName() {
        return Templates.AUTH_LOGIN;
    }

    @PostMapping(Routes.ERROR)
    public String loginError(
            @ModelAttribute(ThymeleafEnv.USERNAME) String username,
            Model model) {
        model
                .addAttribute(ThymeleafEnv.USERNAME, username)
                .addAttribute(ThymeleafEnv.STATUS_CODE, 1);

        return Templates.AUTH_LOGIN;
    }

}
