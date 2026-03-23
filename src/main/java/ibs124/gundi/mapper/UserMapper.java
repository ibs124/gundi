package ibs124.gundi.mapper;

import ibs124.gundi.model.application.dto.TokenDto;
import ibs124.gundi.model.application.dto.RegisterDto;
import ibs124.gundi.model.application.dto.UserDto;
import ibs124.gundi.model.entity.UserEntity;
import ibs124.gundi.model.entity.VerificationTokenEntity;
import ibs124.gundi.security.MyUserDetails;

public interface UserMapper {

    UserEntity mapToEntity(RegisterDto dto);

    UserDto mapToDto(UserEntity src);

    TokenDto mapToDto(VerificationTokenEntity token);

    MyUserDetails mapToSecurityModel(UserEntity src);
}
