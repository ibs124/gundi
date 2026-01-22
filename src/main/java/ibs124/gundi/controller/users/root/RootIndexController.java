package ibs124.gundi.controller.users.root;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ibs124.gundi.constant.Routes;
import ibs124.gundi.constant.Templates;
import ibs124.gundi.constant.ThymeleafAttributes;
import ibs124.gundi.service.test.verification.VerificationEmailSender;
import ibs124.gundi.utility.RouteUtils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping(Routes.ROOT)
public class RootIndexController {

    private static final String VAR_TEST_ROUTES = "test_routes";
    private static final String VAR_SEND_TEST_EMAIL = "send_email";
    private static final String MESSAGE_TEST_EMAIL_SENT = "Email test verification sent, check inbox.";

    private final VerificationEmailSender emailSender;

    public RootIndexController(VerificationEmailSender verificationEmailSender) {
        this.emailSender = verificationEmailSender;
    }

    @GetMapping
    public String index() {
        return Templates.ROOT_INDEX;
    }

    @GetMapping(VAR_SEND_TEST_EMAIL)
    public String sendTestEmail(Model model) {
        this.emailSender.sendTestingVerificationTemail();
        model.addAttribute(ThymeleafAttributes.MESSAGE, MESSAGE_TEST_EMAIL_SENT);
        return this.index();
    }

    @ModelAttribute(VAR_TEST_ROUTES)
    public Map<String, String> globalRoutes() {
        return Map.of(
                VAR_SEND_TEST_EMAIL, Routes.ROOT + "/" + VAR_SEND_TEST_EMAIL);
    }

}
