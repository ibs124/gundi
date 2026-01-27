package ibs124.gundi.controller.users;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import ibs124.gundi.constant.Routes;
import ibs124.gundi.constant.ThTemplates;
import ibs124.gundi.constant.ThEnv;
import ibs124.gundi.mapper.UserMapper;
import ibs124.gundi.model.application.UserDetailsDto;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
class ProfileController {

    private final UserMapper userMapper;

    public ProfileController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping(Routes.USERS_SELF_PROFILE)
    public String index(
            @AuthenticationPrincipal UserDetailsDto principal,
            Model model) {

        if (!model.containsAttribute(ThEnv.VIEW_MODEL)) {
            model
                    .addAttribute(
                            ThEnv.VIEW_MODEL,
                            this.userMapper.mapToPresentationModel(principal));
        }

        return ThTemplates.USERS_SELF_PROFILE;
    }

}
