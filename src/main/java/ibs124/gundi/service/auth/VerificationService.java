package ibs124.gundi.service.auth;

import ibs124.gundi.model.application.TokenDto;

public interface VerificationService {

    TokenDto verifyBySecret(String secret);
}
