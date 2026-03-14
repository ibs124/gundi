package ibs124.gundi.service.auth;

import ibs124.gundi.model.application.TokenDto;

public interface VerificationTokenConfigService {

    TokenDto configureNewUserVerificationToken();

}