package ibs124.gundi.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ibs124.gundi.constant.Routes;
import ibs124.gundi.constant.ThymeleafEnv;
import ibs124.gundi.constant.Templates;

@Controller
@RequestMapping(Routes.VERIFICATION)
class VerificationController {

    @GetMapping(Routes.SEND)
    public String send(Model model) {
        model.addAttribute(ThymeleafEnv.MESSAGE, "Verification email sent");
        return Templates.VERIFICATION;
    }

    @GetMapping(Routes.SUBMIT)
    public String submit(Model model) {
        model.addAttribute(ThymeleafEnv.MESSAGE, "Verification code submited!");
        return Templates.VERIFICATION;
    }

}
