package ibs124.gundi.controller.users;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ibs124.gundi.constant.Routes;
import ibs124.gundi.constant.Templates;
import ibs124.gundi.constant.ThymeleafEnv;
import ibs124.gundi.mapper.UserMapper;
import ibs124.gundi.model.application.UserDetailsDto;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping(Routes.USERS_SELF_ACCOUNT)
public class AccountController {

    private final UserMapper userMapper;

    public AccountController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping
    public String index(
            @AuthenticationPrincipal UserDetailsDto principal,
            Model model) {

        if (!model.containsAttribute(ThymeleafEnv.VIEW_MODEL)) {
            model
                    .addAttribute(
                            ThymeleafEnv.VIEW_MODEL,
                            this.userMapper.mapToPresentationModel(principal));
        }

        return Templates.USERS_SELF_ACCOUNT;
    }

}
