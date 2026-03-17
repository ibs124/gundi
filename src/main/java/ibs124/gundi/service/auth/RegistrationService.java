package ibs124.gundi.service.auth;

import ibs124.gundi.model.application.dto.RegisterDto;
import ibs124.gundi.model.application.dto.RegisterResponseDto;

public interface RegistrationService {

    RegisterResponseDto register(RegisterDto request);

}
