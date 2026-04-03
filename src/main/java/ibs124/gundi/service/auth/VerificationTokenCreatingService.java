package ibs124.gundi.service.auth;

import ibs124.gundi.model.dto.auth.TokenContract;

public interface VerificationTokenCreatingService {

    TokenContract createById(Long id);

    TokenContract create(String username);

}