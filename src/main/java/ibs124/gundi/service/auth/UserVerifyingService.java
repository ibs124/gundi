package ibs124.gundi.service.auth;

import ibs124.gundi.model.application.dto.UserVerifiedResponseDto;

public interface UserVerifyingService {

    UserVerifiedResponseDto verifyBySecret(String secret);
}
