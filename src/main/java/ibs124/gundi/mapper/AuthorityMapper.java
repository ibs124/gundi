package ibs124.gundi.mapper;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import ibs124.gundi.model.domain.Authority;
import ibs124.gundi.model.enumm.Role;

public interface AuthorityMapper {

    GrantedAuthority mapToSecurityModel(Authority src);

    Collection<GrantedAuthority> mapToSecurityModelAll(Collection<Authority> src);

    Role mapToEnum(Authority role);

    Collection<Role> mapToEnumAll(Collection<Authority> role);
}
