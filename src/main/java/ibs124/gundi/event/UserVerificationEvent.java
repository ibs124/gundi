package ibs124.gundi.event;

import ibs124.gundi.model.application.VerificationSendDto;

public class UserVerificationEvent extends AbstractVerificationEvent {

    public UserVerificationEvent(VerificationSendDto verificationRequest) {
        super(verificationRequest);
    }

}
