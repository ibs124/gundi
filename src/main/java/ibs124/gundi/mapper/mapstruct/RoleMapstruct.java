package ibs124.gundi.mapper.mapstruct;

import org.mapstruct.Mapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import ibs124.gundi.config.MapperConfig;
import ibs124.gundi.mapper.RoleMapper;
import ibs124.gundi.model.domain.Role;

@Mapper(componentModel = MapperConfig.MAPSTRUCT_COMPONENT_MODEL)
public interface RoleMapstruct extends RoleMapper {

    @Override
    default GrantedAuthority mapToInfrastructureModel(Role src) {
        return src == null ? null : new SimpleGrantedAuthority("ROLE_" + src.getName());
    }
}
