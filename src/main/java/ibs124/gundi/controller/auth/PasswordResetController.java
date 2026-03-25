package ibs124.gundi.controller.auth;

import static ibs124.gundi.constant.ThymeleafEnv.STATUS_CODE;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ibs124.gundi.constant.Routes;
import ibs124.gundi.constant.Templates;
import ibs124.gundi.event.UserPasswordResetEvent;
import ibs124.gundi.model.dto.auth.TokenDto;
import ibs124.gundi.service.auth.PasswordResetTokenIssuingService;
import ibs124.gundi.util.RouteUtils;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
class PasswordResetController {

    private static final int STATUS_CODE_EMAIL_SENT = 2;

    private final PasswordResetTokenIssuingService tokenIssuingService;
    private final ApplicationEventPublisher eventPublisher;

    public PasswordResetController(
            PasswordResetTokenIssuingService tokenIssuingService,
            ApplicationEventPublisher eventPublisher) {
        this.tokenIssuingService = tokenIssuingService;
        this.eventPublisher = eventPublisher;
    }

    @GetMapping(Routes.AUTH_PASSWORD_RESET)
    public String index() {
        return Templates.AUTH_PASSWORD_RESET;
    }

    @PostMapping(Routes.AUTH_PASSWORD_RESET)
    public String sent(
            @RequestParam String email,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute(STATUS_CODE, STATUS_CODE_EMAIL_SENT);

        TokenDto token = this.tokenIssuingService
                .issueByUsername(email);

        if (token != null) {
            String appUrl = RouteUtils.getAppUrl(request);
            this.eventPublisher.publishEvent(
                    new UserPasswordResetEvent(token.username(), token.secret(), appUrl));
        }

        return RouteUtils.getRedirectUrl(Routes.AUTH_LOGIN);
    }

    @GetMapping(Routes.AUTH_PASSWORD_RESSET_SUBMIT)
    public String getMethodName(
            @RequestParam(name = Routes.REQUEST_PARAM_TOKEN, required = false) String token) {

        return RouteUtils.getRedirectUrl(Routes.AUTH_LOGIN);
    }

}
