package ibs124.gundi.mapper;

import ibs124.gundi.model.api.RegisterRequest;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.model.dto.RegisterDto;
import ibs124.gundi.model.dto.UserDetailsImpl;
import ibs124.gundi.model.dto.UserDto;

public interface UserMapper {

    RegisterDto toServiceModel(RegisterRequest src);

    UserDto toServiceModel(User src);

    User toDomainModel(RegisterDto src);

    UserDetailsImpl toSecurityModel(User src);

}
