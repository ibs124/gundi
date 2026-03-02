package ibs124.gundi.mapper.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ibs124.gundi.mapper.UserMapper;
import ibs124.gundi.constant.Mappers;
import ibs124.gundi.mapper.RoleMapper;
import ibs124.gundi.model.application.RegisterDto;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.model.presentation.UserRegisterRequest;
import ibs124.gundi.security.MyUserDetails;

@Mapper(componentModel = Mappers.COMPONENT_MODEL, uses = RoleMapper.class)
interface UserMapstruct extends UserMapper {

    @Override
    @Mapping(source = Mappers.ROLES, target = Mappers.AUTHORITIES)
    MyUserDetails mapToSecurityModel(User x);

    @Override
    @Mapping(source = Mappers.EMAIL, target = Mappers.PRIMARY_EMAIL)
    RegisterDto mapToApplicationModel(UserRegisterRequest src);

}
