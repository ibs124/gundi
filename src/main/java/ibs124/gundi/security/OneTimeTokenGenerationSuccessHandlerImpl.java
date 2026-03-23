package ibs124.gundi.security;

import java.io.IOException;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.ott.OneTimeToken;
import org.springframework.security.web.authentication.ott.OneTimeTokenGenerationSuccessHandler;
import org.springframework.security.web.authentication.ott.RedirectOneTimeTokenGenerationSuccessHandler;
import org.springframework.stereotype.Component;

import ibs124.gundi.constant.Routes;
import ibs124.gundi.event.UserVerificationEvent;
import ibs124.gundi.util.RouteUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OneTimeTokenGenerationSuccessHandlerImpl implements
        OneTimeTokenGenerationSuccessHandler {

    private final ApplicationEventPublisher eventPublisher;
    private final OneTimeTokenGenerationSuccessHandler redirectHandler;

    public OneTimeTokenGenerationSuccessHandlerImpl(
            ApplicationEventPublisher eventPublisher) {

        this.eventPublisher = eventPublisher;
        this.redirectHandler = new RedirectOneTimeTokenGenerationSuccessHandler(
                Routes.VERIFICATION_SEND);
    }

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            OneTimeToken oneTimeToken) throws IOException, ServletException {

        UserVerificationEvent event = new UserVerificationEvent(
                oneTimeToken.getUsername(),
                oneTimeToken.getTokenValue(),
                RouteUtils.getAppUrl(request));

        this.eventPublisher.publishEvent(event);

        this.redirectHandler.handle(request, response, oneTimeToken);
    }

}