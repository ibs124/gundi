package ibs124.gundi.service.auth.component;

import ibs124.gundi.model.domain.User;
import ibs124.gundi.model.domain.VerificationToken;

public interface VerificationTokenCreator {

    VerificationToken createByUser(User user);

}
