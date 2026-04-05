package ibs124.gundi.controller.auth;

import static ibs124.gundi.constant.ThymeleafEnv.ROUTES;
import static ibs124.gundi.constant.ThymeleafEnv.STATUS_CODE;

import java.io.ObjectInputFilter.Status;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ibs124.gundi.constant.Routes;
import ibs124.gundi.constant.Templates;
import ibs124.gundi.event.UserPasswordResetEvent;
import ibs124.gundi.model.dto.auth.PasswordResetDto;
import ibs124.gundi.model.dto.auth.TokenContract;
import ibs124.gundi.model.dto.auth.TokenCreateRequest;
import ibs124.gundi.model.presentation.PasswordResetRequest;
import ibs124.gundi.model.presentation.StatusCode;
import ibs124.gundi.service.auth.token.TokenCreationProviderService;
import ibs124.gundi.service.auth.token.TokenValidationProviderService;
import ibs124.gundi.util.RouteUtils;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
class PasswordResetController {

    private static final int STATUS_CODE_EMAIL_SENT = 2;

    private final TokenCreationProviderService<TokenCreateRequest> tokenCreator;
    private final ApplicationEventPublisher eventPublisher;
    private final TokenValidationProviderService<PasswordResetDto> validationProvider;

    public PasswordResetController(TokenCreationProviderService<TokenCreateRequest> tokenCreator,
            ApplicationEventPublisher eventPublisher,
            TokenValidationProviderService<PasswordResetDto> validationProvider) {
        this.tokenCreator = tokenCreator;
        this.eventPublisher = eventPublisher;
        this.validationProvider = validationProvider;
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

        redirectAttributes.addFlashAttribute(StatusCode.custom(STATUS_CODE_EMAIL_SENT));

        TokenContract token = this.tokenCreator
                .create(() -> email);

        if (token != null) {
            String appUrl = RouteUtils.getAppUrl(request);
            this.eventPublisher.publishEvent(
                    new UserPasswordResetEvent(token.getUsername(), token.getSecret(), appUrl));
        }

        return RouteUtils.getRedirectUrl(Routes.AUTH_LOGIN);
    }

    @GetMapping(Routes.AUTH_PASSWORD_RESSET_SUBMIT)
    public String getSubmit(PasswordResetRequest request) {

        boolean tokenIsValid = this.validationProvider
                .isTokenValid(PasswordResetDto
                        .tokenValidateRequest(request.token()));

        return RouteUtils.getRedirectUrl(Routes.AUTH_LOGIN);
    }

    @GetMapping(Routes.AUTH_PASSWORD_RESET_SUCCESS)
    public String getSuccess(Model model) {
        model.addAttribute(StatusCode.success());
        return RouteUtils.getRedirectUrl(Routes.AUTH_LOGIN);
    }

    @GetMapping(Routes.AUTH_PASSWORD_RESET_ERROR)
    public String getError(Model model) {
        model.addAttribute(StatusCode.failure());
        return Templates.AUTH_PASSWORD_RESET;
    }
}
