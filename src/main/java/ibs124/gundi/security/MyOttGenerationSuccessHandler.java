package ibs124.gundi.security;

import java.io.IOException;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.ott.OneTimeToken;
import org.springframework.security.web.authentication.ott.OneTimeTokenGenerationSuccessHandler;
import org.springframework.stereotype.Component;

import ibs124.gundi.event.NewUserVerificationEvent;
import ibs124.gundi.util.RouteUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class MyOttGenerationSuccessHandler implements
        OneTimeTokenGenerationSuccessHandler {

    private final ApplicationEventPublisher eventPublisher;

    public MyOttGenerationSuccessHandler(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            OneTimeToken oneTimeToken) throws IOException, ServletException {

        new NewUserVerificationEvent(
                oneTimeToken.getUsername(),
                oneTimeToken.getTokenValue(),
                RouteUtils.getAppUrl(request));

        this.eventPublisher.publishEvent(oneTimeToken);
    }

}