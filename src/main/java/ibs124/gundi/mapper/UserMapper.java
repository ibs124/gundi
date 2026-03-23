package ibs124.gundi.mapper;

import ibs124.gundi.model.dto.UserRegisterDto;
import ibs124.gundi.model.dto.TokenDto;
import ibs124.gundi.model.dto.UserDto;
import ibs124.gundi.model.entity.UserEntity;
import ibs124.gundi.model.entity.VerificationTokenEntity;
import ibs124.gundi.security.UserDetailsImpl;

public interface UserMapper {

    UserEntity mapToEntity(UserRegisterDto dto);

    UserDto mapToDto(UserEntity src);

    TokenDto mapToDto(VerificationTokenEntity token);

    UserDetailsImpl mapToSecurityModel(UserEntity src);
}
