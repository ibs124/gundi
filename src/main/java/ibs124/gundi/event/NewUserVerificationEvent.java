package ibs124.gundi.event;

import ibs124.gundi.model.application.VerificationSendDto;

public class NewUserVerificationEvent extends AbstractVerificationEvent {

    public NewUserVerificationEvent(VerificationSendDto payload) {
        super(payload);
    }

}
