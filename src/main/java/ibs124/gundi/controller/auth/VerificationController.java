package ibs124.gundi.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ibs124.gundi.constant.Routes;
import ibs124.gundi.constant.ThTemplates;

@Controller
@RequestMapping(Routes.VERIFICATION)
class VerificationController {

    @GetMapping
    public String index() {
        return ThTemplates.VERIFICATION;
    }

    @GetMapping(Routes.SEND)
    public String send() {
        return new String();
    }

}
