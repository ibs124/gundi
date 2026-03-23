package ibs124.gundi.mapper.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ibs124.gundi.mapper.UserMapper;
import ibs124.gundi.constant.Mappers;
import ibs124.gundi.mapper.AuthorityMapper;
import ibs124.gundi.model.dto.UserRegisterDto;
import ibs124.gundi.model.dto.UserDto;
import ibs124.gundi.model.entity.UserEntity;

@Mapper(componentModel = Mappers.COMPONENT_MODEL, uses = AuthorityMapper.class)
interface UserMapstruct extends UserMapper {

    @Override
    @Mapping(source = Mappers.EMAIL, target = Mappers.PRIMARY_EMAIL)
    UserEntity mapToEntity(UserRegisterDto src);

    @Mapping(source = "enabled", target = "isEnabled")
    @Override
    UserDto mapToDto(UserEntity src);
}
