package ibs124.gundi.controller.auth;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ibs124.gundi.config.RouteConfig;
import ibs124.gundi.config.thymeleaf.AttributeConfig;
import ibs124.gundi.config.thymeleaf.TemplateConfig;
import ibs124.gundi.mapper.UserMapper;
import ibs124.gundi.model.application.UserDetailsDto;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping(RouteConfig.USERS_ME)
class UserMeController {

    private final UserMapper userMapper;

    public UserMeController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping
    public String meGet(
            @AuthenticationPrincipal UserDetailsDto principal,
            Model model) {

        if (!model.containsAttribute(AttributeConfig.VIEW_MODEL)) {
            model
                    .addAttribute(
                            AttributeConfig.VIEW_MODEL,
                            this.userMapper.mapToPresentationModel(principal));
        }

        return TemplateConfig.USERS_ME;
    }

}
