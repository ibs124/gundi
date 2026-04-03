package ibs124.gundi.controller.auth;

import static ibs124.gundi.constant.ThymeleafEnv.STATUS_CODE;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ibs124.gundi.constant.Routes;
import ibs124.gundi.constant.Templates;
import ibs124.gundi.event.UserPasswordResetEvent;
import ibs124.gundi.model.dto.auth.TokenContract;
import ibs124.gundi.model.presentation.PasswordResetRequest;
import ibs124.gundi.service.auth.PasswordResetTokenCreatingService;
import ibs124.gundi.service.auth.PasswordResetValidationService;
import ibs124.gundi.util.RouteUtils;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
class PasswordResetController {

    private static final int STATUS_CODE_EMAIL_SENT = 2;

    private final PasswordResetTokenCreatingService tokenIssuingService;
    private final ApplicationEventPublisher eventPublisher;
    private final PasswordResetValidationService passwordResetValidationService;

    public PasswordResetController(
            PasswordResetTokenCreatingService tokenIssuingService,
            ApplicationEventPublisher eventPublisher,
            PasswordResetValidationService passwordResetValidationService) {
        this.tokenIssuingService = tokenIssuingService;
        this.eventPublisher = eventPublisher;
        this.passwordResetValidationService = passwordResetValidationService;
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

        TokenContract token = this.tokenIssuingService
                .create(email);

        if (token != null) {
            String appUrl = RouteUtils.getAppUrl(request);
            this.eventPublisher.publishEvent(
                    new UserPasswordResetEvent(token.getUsername(), token.getSecret(), appUrl));
        }

        return RouteUtils.getRedirectUrl(Routes.AUTH_LOGIN);
    }

    @GetMapping(Routes.AUTH_PASSWORD_RESSET_SUBMIT)
    public String getSubmit(PasswordResetRequest request) {

        boolean tokenIsValid = this.passwordResetValidationService
                .isPasswordResetTokenValid(request.token());

        return RouteUtils.getRedirectUrl(Routes.AUTH_LOGIN);
    }

    @GetMapping(Routes.AUTH_PASSWORD_RESET_SUCCESS)
    public String getSuccess(@RequestParam String param) {
        return new String();
    }

    @GetMapping(Routes.AUTH_PASSWORD_RESET_ERROR)
    public String getError(@RequestParam String param) {
        return new String();
    }
}
