package ibs124.gundi.service.user;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import ibs124.gundi.exception.ResourceCreatingException;
import ibs124.gundi.mapper.UserRoleMapper;
import ibs124.gundi.model.domain.UserRole;
import ibs124.gundi.model.dto.UserRoleDto;
import ibs124.gundi.model.enumm.UserRoleType;
import ibs124.gundi.repository.UserRoleRepository;

@Service
class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository roleRepository;
    private final UserRoleMapper roleMapper;

    UserRoleServiceImpl(UserRoleRepository userRoleRepository, UserRoleMapper roleMapper) {
        this.roleRepository = userRoleRepository;
        this.roleMapper = roleMapper;
    }

    public List<UserRoleDto> initializeUserRoles() {
        try {
            this.roleRepository.deleteAll();

            List<UserRole> roles = Arrays.stream(UserRoleType.values())
                    .map(x -> new UserRole(x))
                    .toList();

            if (roles == null) {
                throw new ResourceCreatingException();
            }

            roles = this.roleRepository.saveAll(roles);

            return this.roleMapper.toServiceModelAll(roles);
        } catch (Exception e) {
            throw new ResourceCreatingException(e);
        }
    }
}
