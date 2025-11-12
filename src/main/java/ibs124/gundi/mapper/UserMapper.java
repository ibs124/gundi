package ibs124.gundi.mapper;

import ibs124.gundi.model.api.RegisterRequest;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.model.dto.RegisterDto;
import ibs124.gundi.model.dto.UserDto;

public interface UserMapper {

    UserDto toServiceModel(User src);

    User toDomainModel(RegisterDto src);

    RegisterDto toServiceModel(RegisterRequest src);

}
