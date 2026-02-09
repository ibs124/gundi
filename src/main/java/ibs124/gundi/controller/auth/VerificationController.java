package ibs124.gundi.controller.auth;

import static ibs124.gundi.constant.ThymeleafEnv.STATUS_CODE;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ibs124.gundi.constant.Routes;
import ibs124.gundi.constant.Templates;

@Controller
@RequestMapping(Routes.VERIFICATION)
class VerificationController {

    @GetMapping(Routes.SEND)
    public String send(Model model) {
        model.addAttribute(STATUS_CODE, 3);
        return Templates.VERIFICATION;
    }

    @GetMapping(Routes.SUBMIT)
    public String submit(Model model) {
        model.addAttribute(STATUS_CODE, 4);
        return Templates.VERIFICATION;
    }

}
