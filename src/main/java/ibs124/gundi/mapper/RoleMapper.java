package ibs124.gundi.mapper;

import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import ibs124.gundi.model.domain.Role;
import ibs124.gundi.model.dto.RoleDTO;

public interface RoleMapper {

    RoleDTO toServiceModel(Role src);

    List<RoleDTO> toServiceModelAll(List<Role> src);

    GrantedAuthority toSecurityModel(Role src);

    Set<GrantedAuthority> toSecurityModelAll(Set<Role> src);
}
