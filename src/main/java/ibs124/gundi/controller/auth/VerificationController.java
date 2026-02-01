package ibs124.gundi.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ibs124.gundi.constant.Routes;
import ibs124.gundi.constant.ThEnv;
import ibs124.gundi.constant.ThTemplates;
import ibs124.gundi.utility.RouteUtils;

@Controller
@RequestMapping(Routes.VERIFICATION)
class VerificationController {

    @GetMapping
    public String index() {
        return ThTemplates.VERIFICATION;
    }

    @GetMapping(Routes.SEND)
    public String send(RedirectAttributes model) {
        model.addFlashAttribute(ThEnv.MESSAGE, "Verification email sent");
        return RouteUtils.getRedirectUrl(Routes.VERIFICATION);
    }

    @GetMapping(Routes.SUBMIT)
    public String submit(RedirectAttributes model) {
        model.addFlashAttribute(ThEnv.MESSAGE, "Verification code submited!");
        return RouteUtils.getRedirectUrl(Routes.VERIFICATION);
    }

}
