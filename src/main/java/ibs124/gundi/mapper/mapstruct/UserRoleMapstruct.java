package ibs124.gundi.mapper.mapstruct;

import org.mapstruct.Mapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import ibs124.gundi.config.MapStructConfig;
import ibs124.gundi.mapper.UserRoleMapper;
import ibs124.gundi.model.domain.UserRole;

@Mapper(componentModel = MapStructConfig.SPRING_COMPONENT_MODEL)
public interface UserRoleMapstruct extends UserRoleMapper {

    @Override
    default GrantedAuthority toSecurityModel(UserRole src) {
        return src == null ? null : new SimpleGrantedAuthority("ROLE_" + src.getType());
    }
}
