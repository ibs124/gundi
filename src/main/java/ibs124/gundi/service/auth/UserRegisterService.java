package ibs124.gundi.service.auth;

import ibs124.gundi.model.dto.auth.UserDto;
import ibs124.gundi.model.dto.auth.UserRegisterDto;

public interface UserRegisterService {

    UserDto register(UserRegisterDto request);

}
