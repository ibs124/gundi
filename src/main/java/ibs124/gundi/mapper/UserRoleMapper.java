package ibs124.gundi.mapper;

import java.util.Set;

import ibs124.gundi.model.domain.UserRole;
import ibs124.gundi.model.dto.UserRoleDto;

public interface UserRoleMapper {

    UserRoleDto toServiceModel(UserRole src);

    UserRole toDomainModel(UserRoleDto src);

    Set<UserRoleDto> toServiceModelAll(Set<UserRole> src);

    Set<UserRole> toDomainModelAll(Set<UserRoleDto> src);

}
