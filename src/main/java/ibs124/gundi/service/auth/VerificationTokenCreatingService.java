package ibs124.gundi.service.auth;

import ibs124.gundi.model.dto.TokenDto;

public interface VerificationTokenCreatingService {

    TokenDto createByUserId(Long id);

    TokenDto createByUsername(String username);

}