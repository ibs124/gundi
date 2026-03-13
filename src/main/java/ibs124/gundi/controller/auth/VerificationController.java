package ibs124.gundi.controller.auth;

import static ibs124.gundi.constant.Routes.VERIFICATION_FAIL;
import static ibs124.gundi.constant.Routes.VERIFICATION_SEND;
import static ibs124.gundi.constant.Routes.VERIFICATION_SUCCESS;
import static ibs124.gundi.constant.ThymeleafEnv.STATUS_CODE;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ibs124.gundi.constant.Routes;
import ibs124.gundi.constant.Templates;
import ibs124.gundi.constant.ThymeleafEnv;
import ibs124.gundi.event.NewUserVerificationEvent;
import ibs124.gundi.security.MyUserDetails;
import ibs124.gundi.service.auth.VerificationService;
import ibs124.gundi.service.auth.VerificationTokenCreatingService;
import ibs124.gundi.util.RouteUtils;
import jakarta.servlet.http.HttpServletRequest;

@Controller
class VerificationController {

    private final VerificationTokenCreatingService verificationTokenCreatingService;
    private final ApplicationEventPublisher eventPublisher;
    private final VerificationService verificationService;

    public VerificationController(VerificationTokenCreatingService verificationTokenCreatingService,
            ApplicationEventPublisher eventPublisher, VerificationService verificationService) {
        this.verificationTokenCreatingService = verificationTokenCreatingService;
        this.eventPublisher = eventPublisher;
        this.verificationService = verificationService;
    }

    @GetMapping(Routes.VERIFICATION)
    public String index() {
        return RouteUtils.getForwardUrl(VERIFICATION_SEND);
    }

    @GetMapping(Routes.VERIFICATION_SEND)
    public String send(
            Model model,
            HttpServletRequest request,
            @AuthenticationPrincipal MyUserDetails principal) {

        String secret = this.verificationTokenCreatingService
                .createVerificationTokenByUserId(principal.getId());

        String appUrl = RouteUtils.getAppUrl(request);

        NewUserVerificationEvent event = new NewUserVerificationEvent(
                principal.getPrimaryEmail(), secret, appUrl);

        this.eventPublisher.publishEvent(event);

        model.addAttribute(STATUS_CODE, 1);
        return Templates.VERIFICATION;
    }

    @GetMapping(Routes.VERIFICATION_SUCCESS)
    public String success(Model model, Authentication authentication) {
        model.addAttribute(STATUS_CODE, 0);
        return Templates.VERIFICATION;
    }

    @GetMapping(Routes.VERIFICATION_FAIL)
    public String error(Model model, Authentication authentication) {
        model.addAttribute(STATUS_CODE, 2);
        return Templates.VERIFICATION;
    }

}
