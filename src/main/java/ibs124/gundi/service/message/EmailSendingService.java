package ibs124.gundi.service.message;

import ibs124.gundi.model.dto.EmailSendDto;

public interface EmailSendingService {

    void sendEmail(EmailSendDto request);
}
