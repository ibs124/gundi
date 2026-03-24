package ibs124.gundi.mapper;

import ibs124.gundi.model.dto.UserRegisterDto;
import ibs124.gundi.model.dto.TokenDto;
import ibs124.gundi.model.dto.UserDto;
import ibs124.gundi.model.dto.UserLoginDetailsDto;
import ibs124.gundi.model.entity.UserEntity;
import ibs124.gundi.model.entity.VerificationTokenEntity;
import ibs124.gundi.security.UserDetailsImpl;

public interface UserMapper {

    UserEntity mapToEntity(UserRegisterDto x);

    UserDto mapToDto(UserEntity x);

    TokenDto mapToDto(VerificationTokenEntity x);

    UserLoginDetailsDto mapToLoginDetailsDto(UserEntity x);

    UserDetailsImpl mapToSecurityModel(UserLoginDetailsDto x);
}
