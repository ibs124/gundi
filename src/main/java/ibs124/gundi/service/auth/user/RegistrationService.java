package ibs124.gundi.service.auth.user;

import ibs124.gundi.model.dto.auth.UserDto;
import ibs124.gundi.model.dto.auth.UserRegisterDto;

public interface RegistrationService {

    UserDto register(UserRegisterDto request);

}
