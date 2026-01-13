package ibs124.gundi.service.auth.domain;

import ibs124.gundi.model.domain.Email;
import ibs124.gundi.model.domain.User;
public interface EmailCreateDomainService {

    Email createPrymaryEmailByUser(User user, String emailAddress);

}
