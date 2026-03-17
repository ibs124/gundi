package ibs124.gundi.service.auth.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import ibs124.gundi.mapper.AuthorityMapper;
import ibs124.gundi.model.application.AuthorityDto;
import ibs124.gundi.model.enumm.Role;
import ibs124.gundi.model.persistence.AuthorityEntity;
import ibs124.gundi.repository.AuthorityRepository;
import ibs124.gundi.service.auth.AuthorityInitService;

@Service
class AuthorityInitServiceImpl implements AuthorityInitService {

    private final AuthorityRepository roleRepository;
    private final AuthorityMapper roleMapper;

    public AuthorityInitServiceImpl(
            AuthorityRepository roleRepository,
            AuthorityMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public Collection<AuthorityDto> init() {

        List<AuthorityEntity> roles = this.roleRepository
                .findAll();

        if (!roles.isEmpty()) {
            return this.roleMapper.mapToApplicationModelAll(roles);
        }

        this.roleRepository.deleteAll();

        roles = Arrays
                .stream(Role.values())
                .map(x -> new AuthorityEntity(x.name()))
                .toList();

        if (roles == null) {
            return new ArrayList<>();
        }

        roles = this.roleRepository.saveAll(roles);

        return this.roleMapper.mapToApplicationModelAll(roles);
    }

}
