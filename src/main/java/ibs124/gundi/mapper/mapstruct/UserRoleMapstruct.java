package ibs124.gundi.mapper.mapstruct;

import org.mapstruct.Mapper;

import ibs124.gundi.config.MapStructConfig;
import ibs124.gundi.mapper.UserRoleMapper;

@Mapper(componentModel = MapStructConfig.SPRING_COMPONENT_MODEL)
public interface UserRoleMapstruct extends UserRoleMapper {

}
