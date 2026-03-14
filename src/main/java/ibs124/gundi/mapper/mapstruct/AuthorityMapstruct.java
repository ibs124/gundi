package ibs124.gundi.mapper.mapstruct;

import org.mapstruct.Mapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import ibs124.gundi.constant.Mappers;
import ibs124.gundi.mapper.AuthorityMapper;
import ibs124.gundi.model.domain.Authority;
import ibs124.gundi.model.enumm.Role;

@Mapper(componentModel = Mappers.COMPONENT_MODEL)
public interface AuthorityMapstruct extends AuthorityMapper {

    @Override
    default GrantedAuthority mapToSecurityModel(Authority src) {
        return src == null
                ? null
                : new SimpleGrantedAuthority(
                        Mappers.ROLE_TO_AUTHORITY_PREFIX + src.getName());
    }

    @Override
    default Role mapToEnum(Authority role) {
        return role.getName();
    }

}
