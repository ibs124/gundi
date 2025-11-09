package ibs124.gundi.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ibs124.gundi.constant.Routes;
import ibs124.gundi.constant.Templates;

@Controller
public class RegisterController {

    @GetMapping(Routes.REGISTER)
    public String getMethodName() {
        return Templates.REGISTER;
    }

}
