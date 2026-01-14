package ibs124.gundi.service.auth;

import ibs124.gundi.model.application.EmailVerificationSendDTO;

public interface EmailSendingService {

    void send(EmailVerificationSendDTO request);
}
