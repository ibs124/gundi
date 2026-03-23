package ibs124.gundi.mapper;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import ibs124.gundi.model.application.dto.AuthorityDto;
import ibs124.gundi.model.entity.AuthorityEntity;

public interface AuthorityMapper {

    SimpleGrantedAuthority mapToSecurityModel(AuthorityEntity src);

    Collection<GrantedAuthority> mapToSecurityModelAll(Collection<AuthorityEntity> src);

    AuthorityDto mapToDto(AuthorityEntity x);

    Collection<AuthorityDto> mapToDtoAll(Collection<AuthorityEntity> x);
}
