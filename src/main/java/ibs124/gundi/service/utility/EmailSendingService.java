package ibs124.gundi.service.utility;

import ibs124.gundi.model.application.EmailSendDto;

public interface EmailSendingService {

    void sendEmail(EmailSendDto request);
}
