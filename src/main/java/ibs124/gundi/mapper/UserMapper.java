package ibs124.gundi.mapper;

import ibs124.gundi.model.dto.auth.TokenDto;
import ibs124.gundi.model.dto.auth.UserDto;
import ibs124.gundi.model.dto.auth.UserLoginDetailsDto;
import ibs124.gundi.model.dto.auth.UserRegisterDto;
import ibs124.gundi.model.dto.user.ProfileDto;
import ibs124.gundi.model.entity.UserEntity;
import ibs124.gundi.model.entity.VerificationTokenEntity;
import ibs124.gundi.security.UserDetailsImpl;

public interface UserMapper {

    ProfileDto mapToProfileDto(UserEntity x);

    UserEntity mapToEntity(UserRegisterDto x);

    UserDto mapToDto(UserEntity x);

    TokenDto mapToDto(VerificationTokenEntity x);

    UserLoginDetailsDto mapToLoginDetailsDto(UserEntity x);

    UserDetailsImpl mapToSecurityModel(UserLoginDetailsDto x);

}
