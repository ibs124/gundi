package ibs124.gundi.service.auth.domain;

import ibs124.gundi.model.domain.User;
import ibs124.gundi.model.domain.VerificationToken;

public interface TokenCreateDomainService {

    VerificationToken createByUser(User user);

}
