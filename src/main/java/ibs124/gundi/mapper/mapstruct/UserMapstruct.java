package ibs124.gundi.mapper.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ibs124.gundi.config.MapStructConfig;
import ibs124.gundi.mapper.UserMapper;
import ibs124.gundi.mapper.UserRoleMapper;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.security.UserDetailsImpl;

@Mapper(componentModel = MapStructConfig.SPRING_COMPONENT_MODEL, uses = UserRoleMapper.class)
interface UserMapstruct extends UserMapper {

    @Mapping(source = "roles", target = "authorities")
    UserDetailsImpl toSecurityModel(User x);
}
