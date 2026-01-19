package ibs124.gundi.controller.auth;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ibs124.gundi.constant.Routes;
import ibs124.gundi.constant.Templates;
import ibs124.gundi.constant.ThymeleafAttributes;
import ibs124.gundi.mapper.UserMapper;
import ibs124.gundi.model.application.UserDetailsDto;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping(Routes.USERS_ME)
class UserMeController {

    private final UserMapper userMapper;

    public UserMeController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping
    public String meGet(
            @AuthenticationPrincipal UserDetailsDto principal,
            Model model) {

        if (!model.containsAttribute(ThymeleafAttributes.VIEW_MODEL)) {
            model
                    .addAttribute(
                            ThymeleafAttributes.VIEW_MODEL,
                            this.userMapper.mapToPresentationModel(principal));
        }

        return Templates.USERS_ME;
    }

}
