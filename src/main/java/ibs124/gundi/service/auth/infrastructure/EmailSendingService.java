package ibs124.gundi.service.auth.infrastructure;

import ibs124.gundi.model.infrastructure.EmailVerificationSendDTO;

public interface EmailSendingService {

    void send(EmailVerificationSendDTO request);
}
