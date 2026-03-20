package ibs124.gundi.mapper.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ibs124.gundi.mapper.UserMapper;
import ibs124.gundi.constant.Mappers;
import ibs124.gundi.mapper.AuthorityMapper;
import ibs124.gundi.model.application.dto.UserCreateDto;
import ibs124.gundi.model.application.dto.UserDto;
import ibs124.gundi.model.persistence.UserEntity;
import ibs124.gundi.model.presentation.RegisterRequest;

@Mapper(componentModel = Mappers.COMPONENT_MODEL, uses = AuthorityMapper.class)
interface UserMapstruct extends UserMapper {

    @Override
    @Mapping(source = Mappers.EMAIL, target = Mappers.PRIMARY_EMAIL)
    UserCreateDto mapToApplicationModel(RegisterRequest src);

    @Mapping(source = "enabled", target = "isEnabled")
    @Override
    UserDto mapToApplicationModel(UserEntity src);
}
