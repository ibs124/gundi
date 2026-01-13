package ibs124.gundi.service.auth.domain;

import ibs124.gundi.model.application.EmailCreateDTO;
import ibs124.gundi.model.application.EmailDTO;
public interface EmailCreateDomainService {

    EmailDTO create(EmailCreateDTO dto);

}
