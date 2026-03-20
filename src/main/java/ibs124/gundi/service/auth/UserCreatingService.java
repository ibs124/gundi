package ibs124.gundi.service.auth;

import ibs124.gundi.model.application.dto.UserCreateDto;
import ibs124.gundi.model.application.dto.UserDto;

public interface UserCreatingService {

    UserDto create(UserCreateDto request);

}
