package ibs124.gundi.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ibs124.gundi.constant.Routes;
import ibs124.gundi.constant.Templates;
import ibs124.gundi.constant.ThymeleafAttributes;

@Controller
@RequestMapping(Routes.LOGIN)
class LoginController {

    @GetMapping
    public String getMethodName() {
        return Templates.LOGIN;
    }

    @PostMapping(Routes.ERROR)
    public String loginError(@ModelAttribute(ThymeleafAttributes.USERNAME) String username, Model model) {
        model
                .addAttribute(ThymeleafAttributes.USERNAME, username)
                .addAttribute(ThymeleafAttributes.SUCCESS, false);

        return Templates.LOGIN;
    }

}
