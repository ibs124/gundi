package ibs124.gundi.mapper.mapstruct;

import org.mapstruct.Mapper;

import ibs124.gundi.mapper.UserMapper;

@Mapper(componentModel = Properties.SPRING_COMPONENT_MODEL)
interface UserMapstruct extends UserMapper {

}
