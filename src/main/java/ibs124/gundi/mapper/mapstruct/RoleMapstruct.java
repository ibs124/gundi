package ibs124.gundi.mapper.mapstruct;

import org.mapstruct.Mapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import ibs124.gundi.constant.Mappers;
import ibs124.gundi.mapper.RoleMapper;
import ibs124.gundi.model.domain.Role;
import ibs124.gundi.model.enumm.RoleName;

@Mapper(componentModel = Mappers.COMPONENT_MODEL)
public interface RoleMapstruct extends RoleMapper {

    @Override
    default GrantedAuthority mapToInfrastructureModel(Role src) {
        return src == null
                ? null
                : new SimpleGrantedAuthority(
                        Mappers.ROLE_TO_AUTHORITY_PREFIX + src.getName());
    }

    @Override
    default RoleName mapToEnum(Role role) {
        return role.getName();
    }

}
