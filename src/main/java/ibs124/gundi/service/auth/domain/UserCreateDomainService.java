package ibs124.gundi.service.auth.domain;

import ibs124.gundi.model.application.UserCreateDTO;
import ibs124.gundi.model.application.UserDTO;

public interface UserCreateDomainService {

    UserDTO create(UserCreateDTO dto);
}
