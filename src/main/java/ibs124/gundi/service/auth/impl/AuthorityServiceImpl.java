package ibs124.gundi.service.auth.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import ibs124.gundi.config.AuthorityConfig;
import ibs124.gundi.mapper.AuthorityMapper;
import ibs124.gundi.model.application.contract.AuthorityContract;
import ibs124.gundi.model.persistence.AuthorityEntity;
import ibs124.gundi.repository.AuthorityRepository;
import ibs124.gundi.service.auth.AuthorityCreatingService;

@Service
class AuthorityServiceImpl implements AuthorityCreatingService {

    private final AuthorityRepository authorityRepository;
    private final AuthorityMapper authorityMapper;

    public AuthorityServiceImpl(
            AuthorityRepository roleRepository,
            AuthorityMapper roleMapper) {
        this.authorityRepository = roleRepository;
        this.authorityMapper = roleMapper;
    }

    @Override
    public Collection<? extends AuthorityContract> create() {
        this.authorityRepository.deleteAll();

        List<AuthorityEntity> authList = this.createNew();

        authList = this.authorityRepository.saveAll(authList);

        return this.authorityMapper.mapToApplicationModelAll(authList);
    }

    private List<AuthorityEntity> createNew() {
        return Arrays
                .stream(AuthorityConfig.DEFAULT_AUTHORITIES)
                .map(x -> new AuthorityEntity(x.getAuthority()))
                .toList();
    }

}
