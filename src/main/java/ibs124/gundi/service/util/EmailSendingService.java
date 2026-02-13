package ibs124.gundi.service.util;

import ibs124.gundi.model.application.EmailSendDto;

public interface EmailSendingService {

    void sendEmail(EmailSendDto request);
}
