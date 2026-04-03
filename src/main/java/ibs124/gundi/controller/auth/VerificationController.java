package ibs124.gundi.controller.auth;

import static ibs124.gundi.constant.Routes.AUTH_VERIFICATION_SEND;
import static ibs124.gundi.constant.ThymeleafEnv.STATUS_CODE;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ibs124.gundi.constant.Routes;
import ibs124.gundi.constant.Templates;
import ibs124.gundi.event.UserVerificationEvent;
import ibs124.gundi.model.dto.auth.VerificationTokenCreateRequest;
import ibs124.gundi.security.UserDetailsImpl;
import ibs124.gundi.service.auth.AbstractTokenCreatingService;
import ibs124.gundi.util.RouteUtils;
import jakarta.servlet.http.HttpServletRequest;

@Controller
class VerificationController {

    private final ApplicationEventPublisher eventPublisher;
    private final AbstractTokenCreatingService<VerificationTokenCreateRequest> tokenCreator;

    public VerificationController(ApplicationEventPublisher eventPublisher,
            AbstractTokenCreatingService<VerificationTokenCreateRequest> tokenCreator) {
        this.eventPublisher = eventPublisher;
        this.tokenCreator = tokenCreator;
    }

    @GetMapping(Routes.AUTH_VERIFICATION)
    public String index() {
        return RouteUtils.getForwardUrl(AUTH_VERIFICATION_SEND);
    }

    @GetMapping(Routes.AUTH_VERIFICATION_SEND)
    public String send(
            Model model,
            HttpServletRequest request,
            @AuthenticationPrincipal UserDetailsImpl principal) {

        String secret = this.tokenCreator
                .create(new VerificationTokenCreateRequest(null, principal.getId()))
                .getSecret();

        String appUrl = RouteUtils.getAppUrl(request);

        UserVerificationEvent event = new UserVerificationEvent(
                principal.getPrimaryEmail(), secret, appUrl);

        this.eventPublisher.publishEvent(event);

        model.addAttribute(STATUS_CODE, 1);
        return Templates.AUTH_VERIFICATION;
    }

    @GetMapping(Routes.AUTH_VERIFICATION_SUCCESS)
    public String success(Model model, Authentication authentication) {
        model.addAttribute(STATUS_CODE, 0);
        return Templates.AUTH_VERIFICATION;
    }

    @GetMapping(Routes.AUTH_VERIFICATION_FAIL)
    public String error(Model model, Authentication authentication) {
        model.addAttribute(STATUS_CODE, 2);
        return Templates.AUTH_VERIFICATION;
    }

}
