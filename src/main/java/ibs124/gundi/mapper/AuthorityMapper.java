package ibs124.gundi.mapper;

import java.util.Collection;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import ibs124.gundi.model.dto.AuthorityContract;
import ibs124.gundi.model.dto.AuthorityDto;
import ibs124.gundi.model.entity.AuthorityEntity;

public interface AuthorityMapper {

    AuthorityDto mapToDto(AuthorityEntity x);

    Collection<AuthorityDto> mapToDtoAll(Collection<AuthorityEntity> x);

    Collection<AuthorityContract> mapToContractDtoAll(Collection<AuthorityEntity> x);

    SimpleGrantedAuthority mapToSecurityModel(AuthorityContract x);

    Collection<SimpleGrantedAuthority> mapToSecurityModel(Collection<AuthorityContract> x);

}
