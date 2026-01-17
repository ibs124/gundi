package ibs124.gundi.mapper.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ibs124.gundi.config.MapperConfig;
import ibs124.gundi.mapper.UserMapper;
import ibs124.gundi.mapper.RoleMapper;
import ibs124.gundi.model.application.UserCreateDto;
import ibs124.gundi.model.application.UserDetailsDto;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.model.presentation.UserRegisterRequest;

@Mapper(componentModel = MapperConfig.MAPSTRUCT_COMPONENT_MODEL, uses = RoleMapper.class)
interface UserMapstruct extends UserMapper {

    @Override
    @Mapping(source = "roles", target = "authorities")
    UserDetailsDto mapToInfrastructureModel(User x);

    @Override
    @Mapping(source = "email", target = "primaryEmail")
    UserCreateDto mapToApplicationModel(UserRegisterRequest src);

}
