package ibs124.gundi.mapper;

import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import ibs124.gundi.model.application.RoleDTO;
import ibs124.gundi.model.domain.Role;

public interface RoleMapper {

    RoleDTO mapToApplicationModel(Role src);

    List<RoleDTO> mapToApplicationModelAll(List<Role> src);

    GrantedAuthority mapToInfrastructureModel(Role src);

    Set<GrantedAuthority> mapToInfrastructureModelAll(Set<Role> src);
}
