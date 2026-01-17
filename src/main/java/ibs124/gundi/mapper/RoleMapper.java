package ibs124.gundi.mapper;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import ibs124.gundi.model.domain.Role;
import ibs124.gundi.model.enumm.RoleName;

public interface RoleMapper {

    GrantedAuthority mapToInfrastructureModel(Role src);

    RoleName mapToEnum(Role role);

    List<RoleName> mapToEnumAll(List<Role> role);
}
