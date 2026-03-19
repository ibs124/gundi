package ibs124.gundi.mapper.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import ibs124.gundi.constant.Mappers;
import ibs124.gundi.mapper.AuthorityMapper;
import ibs124.gundi.model.persistence.AuthorityEntity;

@Mapper(componentModel = Mappers.COMPONENT_MODEL)
public interface AuthorityMapstruct extends AuthorityMapper {

    @Mapping(source = "name", target = "authority")
    @Override
    SimpleGrantedAuthority mapToSecurityModel(AuthorityEntity src);

}
