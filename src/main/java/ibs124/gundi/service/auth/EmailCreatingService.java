package ibs124.gundi.service.auth;

import ibs124.gundi.model.application.EmailCreateDTO;
import ibs124.gundi.model.application.EmailDTO;
public interface EmailCreatingService {

    EmailDTO create(EmailCreateDTO dto);

}
