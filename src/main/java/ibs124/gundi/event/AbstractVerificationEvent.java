package ibs124.gundi.event;

import org.springframework.context.ApplicationEvent;

import ibs124.gundi.model.application.VerificationSendDto;

public class AbstractVerificationEvent extends ApplicationEvent {

    private final VerificationSendDto verificationRequest;

    public AbstractVerificationEvent(VerificationSendDto verificationRequest) {
        super(verificationRequest);
        this.verificationRequest = verificationRequest;
    }

    public VerificationSendDto getVerificationRequest() {
        return verificationRequest;
    }

}
