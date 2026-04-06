package ibs124.gundi.controller.auth;

import static ibs124.gundi.constant.ThymeleafEnv.ROUTES;
import static ibs124.gundi.constant.ThymeleafEnv.STATUS_CODE;

import java.io.ObjectInputFilter.Status;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ibs124.gundi.constant.Messages;
import ibs124.gundi.constant.Routes;
import ibs124.gundi.constant.Templates;
import ibs124.gundi.event.UserPasswordResetEvent;
import ibs124.gundi.model.dto.auth.PasswordResetDto;
import ibs124.gundi.model.dto.auth.TokenContract;
import ibs124.gundi.model.dto.auth.TokenCreateRequest;
import ibs124.gundi.model.presentation.Alert;
import ibs124.gundi.model.presentation.PasswordResetRequest;
import ibs124.gundi.model.presentation.StatusCode;
import ibs124.gundi.service.auth.token.TokenCreationProviderService;
import ibs124.gundi.service.auth.token.TokenValidationProviderService;
import ibs124.gundi.util.PresentationUtils;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
class PasswordResetController {

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

    @GetMapping(Routes.PASSWORD_RESET)
    public String index() {
        return Templates.PASSWORD_RESET;
    }

    @PostMapping(Routes.PASSWORD_RESET)
    public String sent(
            @RequestParam String email,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes) {

        TokenContract token = this.tokenCreator
                .create(() -> email);

        if (token != null) {
            String appUrl = PresentationUtils.appUrlBy(request);
            this.eventPublisher.publishEvent(
                    new UserPasswordResetEvent(token.getUsername(), token.getSecret(), appUrl));
        }

        PresentationUtils
                .alert(redirectAttributes, Alert.info(Messages.PASSWORD_RESET_SENT));

        return PresentationUtils.redirect(Routes.LOGIN);

    }

    @GetMapping(Routes.PASSWORD_RESSET_SUBMIT)
    public String getSubmit(PasswordResetRequest request) {

        boolean tokenIsValid = this.validationProvider
                .isTokenValid(PasswordResetDto
                        .tokenValidateRequest(request.token()));

        return PresentationUtils.redirect(Routes.LOGIN);
    }

    @GetMapping(Routes.PASSWORD_RESET_SUCCESS)
    public String getSuccess(Model model) {
        PresentationUtils.alert(model, Alert.success(Messages.PASSWORD_RESET_SUCCESS));
        return PresentationUtils.redirect(Routes.LOGIN);
    }

    @GetMapping(Routes.PASSWORD_RESET_ERROR)
    public String getError(Model model) {
        PresentationUtils.alert(model, Alert.danger(Messages.PASSWORD_RESET_ERROR));
        return Templates.PASSWORD_RESET;
    }
}
