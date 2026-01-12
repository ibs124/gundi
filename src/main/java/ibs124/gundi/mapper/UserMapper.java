package ibs124.gundi.mapper;

import ibs124.gundi.model.api.RegisterRequest;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.model.dto.UserCreateDTO;
import ibs124.gundi.model.dto.UserDTO;
import ibs124.gundi.model.dto.UserDetailsDTO;

public interface UserMapper {

    UserCreateDTO toServiceModel(RegisterRequest src);

    UserDTO toServiceModel(User src);

    UserDetailsDTO toSecurityModel(User src);

    User toDomainModel(UserCreateDTO dto);

}
