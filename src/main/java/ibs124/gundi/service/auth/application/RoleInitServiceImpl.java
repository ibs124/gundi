package ibs124.gundi.service.auth.application;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import ibs124.gundi.mapper.RoleMapper;
import ibs124.gundi.model.application.RoleDTO;
import ibs124.gundi.model.domain.Role;
import ibs124.gundi.model.enumm.RoleName;
import ibs124.gundi.repository.RoleRepository;

@Service
class RoleInitServiceImpl implements RoleInitService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleInitServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public List<RoleDTO> init() {

        List<Role> roles = this.roleRepository.findAll();

        if (roles.size() == RoleName.values().length) {
            return this.roleMapper.mapToApplicationModelAll(roles);
        }

        this.roleRepository.deleteAll();

        roles = this.createFromEnum();

        if (roles == null) {
            throw new IllegalStateException();
        }

        roles = this.roleRepository.saveAll(roles);

        return this.roleMapper.mapToApplicationModelAll(roles);
    }

    private List<Role> createFromEnum() {
        return Arrays
                .stream(RoleName.values())
                .map(x -> new Role(x))
                .toList();
    }

}
