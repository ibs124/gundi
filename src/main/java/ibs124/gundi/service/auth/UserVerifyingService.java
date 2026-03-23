package ibs124.gundi.service.auth;

import ibs124.gundi.model.dto.TokenDto;

public interface UserVerifyingService {

    TokenDto verifyBySecret(String secret);
}
