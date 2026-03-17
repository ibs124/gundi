package ibs124.gundi.service.message;

import ibs124.gundi.model.application.dto.EmailSendDto;

public interface EmailSendingService {

    void sendEmail(EmailSendDto request);
}
