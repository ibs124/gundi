package ibs124.gundi.service.auth;

import ibs124.gundi.model.application.RegisterDto;
import ibs124.gundi.model.application.RegisterResponseDto;

public interface RegistrationService {

    RegisterResponseDto registerUser(RegisterDto request);

}
