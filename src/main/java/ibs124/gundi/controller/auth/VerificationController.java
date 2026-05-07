package ibs124.gundi.controller.auth;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ibs124.gundi.constant.Messages;
import ibs124.gundi.constant.Routes;
import ibs124.gundi.constant.Templates;
import ibs124.gundi.event.UserVerificationEvent;
import ibs124.gundi.model.dto.auth.VerificationTokenCreateRequest;
import ibs124.gundi.model.presentation.Alert;
import ibs124.gundi.security.UserDetailsImpl;
import ibs124.gundi.service.auth.token.TokenCreationProviderService;
import ibs124.gundi.util.PresentationUtils;
import jakarta.servlet.http.HttpServletRequest;

@Controller
class VerificationController {

    private final ApplicationEventPublisher eventPublisher;
    private final TokenCreationProviderService<VerificationTokenCreateRequest> tokenCreator;

    public VerificationController(ApplicationEventPublisher eventPublisher,
            TokenCreationProviderService<VerificationTokenCreateRequest> tokenCreator) {
        this.eventPublisher = eventPublisher;
        this.tokenCreator = tokenCreator;
    }

    @GetMapping(Routes.VERIFICATION_SEND)
    public String send(
            Model model,
            HttpServletRequest request,
            @AuthenticationPrincipal UserDetailsImpl principal) {

        String secret = this.tokenCreator
                .create(new VerificationTokenCreateRequest(null, principal.getId()))
                .getSecret();

        UserVerificationEvent event = new UserVerificationEvent(
                principal.getPrimaryEmail(),
                secret,
                PresentationUtils.appUrlBy(request));

        this.eventPublisher.publishEvent(event);

        PresentationUtils.alert(model, Alert.info(Messages.VERIFICATION_SENT));

        return Templates.VERIFICATION;
    }

    @GetMapping(Routes.VERIFICATION_ERROR)
    public String error(Model model, Authentication authentication) {
        PresentationUtils.alert(model, Alert.danger(Messages.VERIFICATION_ERROR));
        return Templates.VERIFICATION;
    }

}
