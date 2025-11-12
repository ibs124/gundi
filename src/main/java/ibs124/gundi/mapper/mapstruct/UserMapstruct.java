package ibs124.gundi.mapper.mapstruct;

import org.mapstruct.Mapper;

import ibs124.gundi.config.MapStructConfig;
import ibs124.gundi.mapper.UserMapper;

@Mapper(componentModel = MapStructConfig.SPRING_COMPONENT_MODEL)
interface UserMapstruct extends UserMapper {

}
