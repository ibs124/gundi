package ibs124.gundi.event;

public class UserVerificationEvent extends AbstractVerificationEvent {

    public UserVerificationEvent(String token, String email, String appUrl) {
        super(token, email, appUrl);
    }
}
