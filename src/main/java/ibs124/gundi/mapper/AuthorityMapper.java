package ibs124.gundi.mapper;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import ibs124.gundi.model.application.AuthorityDto;
import ibs124.gundi.model.domain.Authority;

public interface AuthorityMapper {

    SimpleGrantedAuthority mapToSecurityModel(Authority src);

    Collection<GrantedAuthority> mapToSecurityModelAll(Collection<Authority> src);

    AuthorityDto mapToApplicationModel(Authority x);

    Collection<AuthorityDto> mapToApplicationModelAll(Collection<Authority> x);
}
