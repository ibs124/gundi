package ibs124.gundi.mapper;

import ibs124.gundi.model.application.RegisterDTO;
import ibs124.gundi.model.application.UserCreateDTO;
import ibs124.gundi.model.application.UserDTO;
import ibs124.gundi.model.application.UserDetailsDTO;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.model.presentation.RegisterRequest;

public interface UserMapper {

    RegisterDTO toServiceModel(RegisterRequest src);

    UserDTO toServiceModel(User src);

    UserDetailsDTO toSecurityModel(User src);

    User toDomainModel(UserCreateDTO dto);

}
