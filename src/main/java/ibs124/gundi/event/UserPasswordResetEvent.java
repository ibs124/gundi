package ibs124.gundi.event;

public class UserPasswordResetEvent extends AbstractVerificationEvent {

    public UserPasswordResetEvent(String email, String secret, String appUrl) {
        super(email, secret, appUrl);
    }
}
