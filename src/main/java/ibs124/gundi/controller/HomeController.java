package ibs124.gundi.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ibs124.gundi.constant.Routes;
import ibs124.gundi.constant.Templates;

@Controller
public class HomeController {

    @GetMapping(Routes.INDEX)
    public String index(Authentication authentication) {
        boolean isGuest = authentication instanceof AnonymousAuthenticationToken
                || authentication == null;

        return isGuest ? Templates.INDEX : Templates.HOME;
    }

}
