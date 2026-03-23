package ibs124.gundi.service.auth;

import ibs124.gundi.model.dto.RegisterDto;
import ibs124.gundi.model.dto.UserDto;

public interface RegistrationService {

    UserDto create(RegisterDto request);

}
