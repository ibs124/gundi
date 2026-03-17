package ibs124.gundi.mapper;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import ibs124.gundi.model.application.AuthorityDto;
import ibs124.gundi.model.persistence.AuthorityEntity;

public interface AuthorityMapper {

    SimpleGrantedAuthority mapToSecurityModel(AuthorityEntity src);

    Collection<GrantedAuthority> mapToSecurityModelAll(Collection<AuthorityEntity> src);

    AuthorityDto mapToApplicationModel(AuthorityEntity x);

    Collection<AuthorityDto> mapToApplicationModelAll(Collection<AuthorityEntity> x);
}
