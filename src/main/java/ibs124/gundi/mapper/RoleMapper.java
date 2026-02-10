package ibs124.gundi.mapper;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import ibs124.gundi.model.domain.Role;
import ibs124.gundi.model.enumm.RoleName;

public interface RoleMapper {

    GrantedAuthority mapToSecurityModel(Role src);

    Collection<GrantedAuthority> mapToSecurityModelAll(Collection<Role> src);

    RoleName mapToEnum(Role role);

    Collection<RoleName> mapToEnumAll(Collection<Role> role);
}
