package ibs124.gundi.security;

import java.io.IOException;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.ott.OneTimeToken;
import org.springframework.security.web.authentication.ott.OneTimeTokenGenerationSuccessHandler;
import org.springframework.security.web.authentication.ott.RedirectOneTimeTokenGenerationSuccessHandler;
import org.springframework.stereotype.Component;

import ibs124.gundi.constant.Routes;
import ibs124.gundi.event.NewUserVerificationEvent;
import ibs124.gundi.util.RouteUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// TODO: Remove after testing is completed.
@SuppressWarnings(value = { "unused" })
@Component
public class MyOttGenerationSuccessHandler implements
        OneTimeTokenGenerationSuccessHandler {

    private final ApplicationEventPublisher eventPublisher;
    private final OneTimeTokenGenerationSuccessHandler redirectHandler;

    public MyOttGenerationSuccessHandler(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
        this.redirectHandler = new RedirectOneTimeTokenGenerationSuccessHandler(
                Routes.VERIFICATION_SEND);
    }

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            OneTimeToken oneTimeToken) throws IOException, ServletException {

        // TODO: Replace std_out printing with event publishing after testing is
        // complete.
        // NewUserVerificationEvent event = new NewUserVerificationEvent(
        // oneTimeToken.getUsername(),
        // oneTimeToken.getTokenValue(),
        // RouteUtils.getAppUrl(request));

        // this.eventPublisher.publishEvent(event);
        System.out.println(
                "\nYour verification token is: " + oneTimeToken.getTokenValue() + "\n");

        this.redirectHandler.handle(request, response, oneTimeToken);
    }

}