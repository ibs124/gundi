package ibs124.gundi.mapper.mapstruct;

import org.mapstruct.Mapper;

import ibs124.gundi.constant.Mappers;
import ibs124.gundi.mapper.AuthorityMapper;

@Mapper(componentModel = Mappers.COMPONENT_MODEL)
public interface AuthorityMapstruct extends AuthorityMapper {

}
