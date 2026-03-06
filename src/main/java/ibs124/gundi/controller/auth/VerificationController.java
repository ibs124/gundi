package ibs124.gundi.controller.auth;

import static ibs124.gundi.constant.ThymeleafEnv.STATUS_CODE;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ibs124.gundi.constant.Routes;
import ibs124.gundi.constant.Templates;
import ibs124.gundi.constant.ThymeleafEnv;

@Controller
@RequestMapping(Routes.VERIFICATION)
class VerificationController {

    @GetMapping(Routes.SEND)
    public String send(Model model, Authentication authentication) {
        model.addAttribute(STATUS_CODE, 3);
        return Templates.VERIFICATION;
    }

    @GetMapping(Routes.SUBMIT)
    public String submit(
            Model model,
            @RequestParam(ThymeleafEnv.TOKEN) String token) {
        model.addAttribute(STATUS_CODE, 4);
        return Templates.VERIFICATION;
    }

}
