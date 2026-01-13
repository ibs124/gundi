package ibs124.gundi.service.auth;

import ibs124.gundi.model.application.UserCreateDTO;
import ibs124.gundi.model.application.UserDTO;

public interface UserCreatingService {

    UserDTO create(UserCreateDTO dto);
}
