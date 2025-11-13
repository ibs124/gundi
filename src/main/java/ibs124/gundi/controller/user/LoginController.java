package ibs124.gundi.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ibs124.gundi.config.RouteConfig;
import ibs124.gundi.config.thymeleaf.AttributeConfig;
import ibs124.gundi.config.thymeleaf.TemplateConfig;

@Controller
@RequestMapping(RouteConfig.LOGIN)
class LoginController {

    @GetMapping
    public String getMethodName() {
        return TemplateConfig.LOGIN;
    }

    @PostMapping(RouteConfig.ERROR)
    public String loginError(@ModelAttribute(AttributeConfig.USERNAME) String username, Model model) {
        model
                .addAttribute(AttributeConfig.USERNAME, username)
                .addAttribute(AttributeConfig.SUCCESS, false);

        return TemplateConfig.LOGIN;
    }

}
