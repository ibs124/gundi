package ibs124.gundi.mapper;

import ibs124.gundi.model.application.UserCreateDTO;
import ibs124.gundi.model.application.UserDTO;
import ibs124.gundi.model.application.UserDetailsDTO;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.model.presentation.RegisterRequest;

public interface UserMapper {

    UserCreateDTO mapToApplicationModel(RegisterRequest src);

    UserDTO mapToApplicationModel(User src);

    UserDetailsDTO mapToInfrastructureModel(User src);

    User mapToDomainModel(UserCreateDTO dto);

}
