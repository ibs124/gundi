package ibs124.gundi.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ibs124.gundi.constant.Routes;
import ibs124.gundi.constant.ThEnv;
import ibs124.gundi.constant.ThTemplates;

@Controller
@RequestMapping(Routes.VERIFICATION)
class VerificationController {

    @GetMapping(Routes.SEND)
    public String send(Model model) {
        model.addAttribute(ThEnv.MESSAGE, "Verification email sent");
        return ThTemplates.VERIFICATION;
    }

    @GetMapping(Routes.SUBMIT)
    public String submit(Model model) {
        model.addAttribute(ThEnv.MESSAGE, "Verification code submited!");
        return ThTemplates.VERIFICATION;
    }

}
