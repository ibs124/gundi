package ibs124.gundi.mapper;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import ibs124.gundi.model.domain.UserRole;
import ibs124.gundi.model.dto.UserRoleDto;

public interface UserRoleMapper {

    UserRoleDto toServiceModel(UserRole src);

    Set<UserRoleDto> toServiceModelAll(Set<UserRole> src);

    UserRole toDomainModel(UserRoleDto src);

    Set<UserRole> toDomainModelAll(Set<UserRoleDto> src);

    GrantedAuthority toSecurityModel(UserRole src);

    Set<GrantedAuthority> toSecurityModelAll(Set<UserRole> src);
}
