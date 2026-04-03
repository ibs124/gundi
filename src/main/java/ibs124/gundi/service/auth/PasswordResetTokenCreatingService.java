package ibs124.gundi.service.auth;

import ibs124.gundi.model.dto.auth.TokenContract;

public interface PasswordResetTokenCreatingService {

    TokenContract create(String username);

}