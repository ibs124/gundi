package ibs124.gundi.service.utility;

import ibs124.gundi.model.application.VerificationSendDto;

public interface VerificationSendingService {

    void sendVerification(VerificationSendDto request);

}