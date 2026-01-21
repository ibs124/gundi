package ibs124.gundi.service.auth.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import ibs124.gundi.mapper.RoleMapper;
import ibs124.gundi.model.domain.Role;
import ibs124.gundi.model.enumm.RoleName;
import ibs124.gundi.repository.RoleRepository;
import ibs124.gundi.service.auth.RoleInitializingService;

@Service
class RoleInitServiceImpl implements RoleInitializingService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleInitServiceImpl(
            RoleRepository roleRepository,
            RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public List<RoleName> initializeRoles() {

        List<Role> roles = this.roleRepository
                .findAll();

        if (roles.size() == RoleName.values().length) {
            return this.roleMapper.mapToEnumAll(roles);

        }

        this.roleRepository.deleteAll();

        roles = Arrays
                .stream(RoleName.values())
                .map(x -> new Role(x))
                .toList();

        roles = this.roleRepository.saveAll(roles);

        return this.roleMapper.mapToEnumAll(roles);

    }

}
