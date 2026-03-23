package ibs124.gundi.event;

public class UserVerificationEvent extends AbstractVerificationEvent {

    public UserVerificationEvent(String email, String secret, String appUrl) {
        super(email, secret, appUrl);
    }
}
