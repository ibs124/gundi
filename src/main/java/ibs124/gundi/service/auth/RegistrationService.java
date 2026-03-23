package ibs124.gundi.service.auth;

import ibs124.gundi.model.application.dto.RegisterDto;
import ibs124.gundi.model.application.dto.UserDto;

public interface RegistrationService {

    UserDto create(RegisterDto request);

}
