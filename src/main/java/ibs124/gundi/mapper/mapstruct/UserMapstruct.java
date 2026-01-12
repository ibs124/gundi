package ibs124.gundi.mapper.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ibs124.gundi.config.MapperConfig;
import ibs124.gundi.mapper.UserMapper;
import ibs124.gundi.mapper.RoleMapper;
import ibs124.gundi.model.api.RegisterRequest;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.model.dto.UserCreateDTO;
import ibs124.gundi.model.dto.UserDetailsDTO;

@Mapper(componentModel = MapperConfig.MAPSTRUCT_COMPONENT_MODEL, uses = RoleMapper.class)
interface UserMapstruct extends UserMapper {

    @Mapping(source = "roles", target = "authorities")
    UserDetailsDTO toSecurityModel(User x);

    @Mapping(source = "email", target = "primaryEmail")
    UserCreateDTO toServiceModel(RegisterRequest src);

}
