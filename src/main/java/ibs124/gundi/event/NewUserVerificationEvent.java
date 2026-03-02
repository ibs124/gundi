package ibs124.gundi.event;

public class NewUserVerificationEvent extends AbstractVerificationEvent {

    public NewUserVerificationEvent(String email, String secret, String appUrl) {
        super(email, secret, appUrl);
    }
}
