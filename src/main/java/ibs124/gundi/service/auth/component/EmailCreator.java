package ibs124.gundi.service.auth.component;

import ibs124.gundi.model.domain.Email;
import ibs124.gundi.model.domain.User;
public interface EmailCreator {

    Email createPrymaryEmailByUser(User user, String emailAddress);

}
