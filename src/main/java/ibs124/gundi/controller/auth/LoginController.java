package ibs124.gundi.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ibs124.gundi.constant.Routes;
import ibs124.gundi.constant.ThTemplates;
import ibs124.gundi.constant.ThAttributes;

@Controller
@RequestMapping(Routes.LOGIN)
class LoginController {

    @GetMapping
    public String getMethodName() {
        return ThTemplates.LOGIN;
    }

    @PostMapping(Routes.ERROR)
    public String loginError(@ModelAttribute(ThAttributes.USERNAME) String username, Model model) {
        model
                .addAttribute(ThAttributes.USERNAME, username)
                .addAttribute(ThAttributes.SUCCESS, false);

        return ThTemplates.LOGIN;
    }

}
