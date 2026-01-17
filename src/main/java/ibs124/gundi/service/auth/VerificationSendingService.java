package ibs124.gundi.service.auth;

import ibs124.gundi.model.application.VerificationSendDto;

public interface VerificationSendingService {

    void sendNewUserVerification(VerificationSendDto request);

}