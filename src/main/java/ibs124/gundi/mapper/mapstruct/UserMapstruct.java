package ibs124.gundi.mapper.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ibs124.gundi.config.MapperConfig;
import ibs124.gundi.mapper.UserMapper;
import ibs124.gundi.mapper.RoleMapper;
import ibs124.gundi.model.application.UserCreateDTO;
import ibs124.gundi.model.application.UserDetailsDTO;
import ibs124.gundi.model.domain.User;

@Mapper(componentModel = MapperConfig.MAPSTRUCT_COMPONENT_MODEL, uses = RoleMapper.class)
interface UserMapstruct extends UserMapper {

    @Mapping(source = "roles", target = "authorities")
    UserDetailsDTO toSecurityModel(User x);

    @Override
    default User toDomainModel(UserCreateDTO src) {
        if (src == null) {
            return null;
        }

        User user = new User();

        user.setPassword(src.password());
        user.setFullName(src.fullName());
        user.setUsername(src.username());

        return user;
    }

}
