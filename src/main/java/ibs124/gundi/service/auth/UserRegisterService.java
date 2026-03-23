package ibs124.gundi.service.auth;

import ibs124.gundi.model.dto.UserRegisterDto;
import ibs124.gundi.model.dto.UserDto;

public interface UserRegisterService {

    UserDto register(UserRegisterDto request);

}
