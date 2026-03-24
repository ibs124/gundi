package ibs124.gundi.mapper.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ibs124.gundi.mapper.UserMapper;
import ibs124.gundi.constant.Mappers;
import ibs124.gundi.mapper.AuthorityMapper;
import ibs124.gundi.model.dto.auth.UserDto;
import ibs124.gundi.model.dto.auth.UserLoginDetailsDto;
import ibs124.gundi.model.dto.auth.UserRegisterDto;
import ibs124.gundi.model.dto.user.ProfileDto;
import ibs124.gundi.model.entity.UserEntity;
import ibs124.gundi.security.UserDetailsImpl;

@Mapper(componentModel = Mappers.COMPONENT_MODEL, uses = AuthorityMapper.class)
interface UserMapstruct extends UserMapper {

    @Mapping(source = Mappers.EMAIL, target = Mappers.PRIMARY_EMAIL)
    @Override
    UserEntity mapToEntity(UserRegisterDto src);

    @Mapping(source = Mappers.ENABLED, target = Mappers.IS_ENABLED)
    @Override
    UserDto mapToDto(UserEntity src);

    @Mapping(source = Mappers.ENABLED, target = Mappers.IS_ENABLED)
    @Override
    UserLoginDetailsDto mapToLoginDetailsDto(UserEntity x);

    @Mapping(source = Mappers.IS_ENABLED, target = Mappers.ENABLED)
    @Override
    UserDetailsImpl mapToSecurityModel(UserLoginDetailsDto x);

    @Mapping(source = Mappers.PRIMARY_EMAIL, target = Mappers.EMAIL)
    @Override
    ProfileDto mapToProfileDto(UserEntity x);

}
