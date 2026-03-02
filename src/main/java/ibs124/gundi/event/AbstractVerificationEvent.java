package ibs124.gundi.event;

import org.springframework.context.ApplicationEvent;

public class AbstractVerificationEvent extends ApplicationEvent {

    private final String email;
    private final String secret;
    private final String appUrl;

    public AbstractVerificationEvent(String email, String secret, String appUrl) {
        super(email);
        this.email = email;
        this.secret = secret;
        this.appUrl = appUrl;
    }

    public String getEmail() {
        return email;
    }

    public String getSecret() {
        return secret;
    }

    public String getAppUrl() {
        return appUrl;
    }

}
