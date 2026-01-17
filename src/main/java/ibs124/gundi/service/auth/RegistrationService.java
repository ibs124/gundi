package ibs124.gundi.service.auth;

import ibs124.gundi.model.application.RegisterDto;

public interface RegistrationService {

    Long registerUser(RegisterDto request);

}
