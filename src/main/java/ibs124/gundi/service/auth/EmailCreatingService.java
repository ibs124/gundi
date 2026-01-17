package ibs124.gundi.service.auth;

import ibs124.gundi.model.application.EmailCreateDto;

public interface EmailCreatingService {

    Long createPrimaryEmail(EmailCreateDto request);

}