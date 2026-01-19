package ibs124.gundi.event;

import org.springframework.context.ApplicationEvent;

public class UserVerificationEvent extends ApplicationEvent {

    private final String token;
    private final String email;
    private final String appUrl;

    public UserVerificationEvent(String token, String email, String appUrl) {
        super(token);
        this.token = token;
        this.email = email;
        this.appUrl = appUrl;
    }

    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }

    public String getAppUrl() {
        return appUrl;
    }

}
