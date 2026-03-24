package ibs124.gundi.controller.auth;

import static ibs124.gundi.constant.ThymeleafEnv.STATUS_CODE;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ibs124.gundi.constant.Routes;
import ibs124.gundi.constant.Templates;
import ibs124.gundi.util.RouteUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
class PasswordResetController {

    @GetMapping(Routes.AUTH_PASSWORD_RESET)
    public String index() {
        return Templates.AUTH_PASSWORD_RESET;
    }

    @PostMapping(Routes.AUTH_PASSWORD_RESET)
    public String sent(
            @RequestParam String email, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute(STATUS_CODE, 2);

        return RouteUtils.getRedirectUrl(Routes.AUTH_LOGIN);
    }

}
