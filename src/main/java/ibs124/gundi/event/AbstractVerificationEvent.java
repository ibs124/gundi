package ibs124.gundi.event;

import org.springframework.context.ApplicationEvent;

import ibs124.gundi.model.application.VerificationSendDto;

public class AbstractVerificationEvent extends ApplicationEvent {

    private final VerificationSendDto payload;

    public AbstractVerificationEvent(VerificationSendDto payload) {
        super(payload);
        this.payload = payload;
    }

    public VerificationSendDto getPayload() {
        return payload;
    }

}
