package ibs124.gundi.service.auth.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import ibs124.gundi.config.AuthorityConfig;
import ibs124.gundi.mapper.AuthorityMapper;
import ibs124.gundi.model.application.Authority;
import ibs124.gundi.model.persistence.AuthorityEntity;
import ibs124.gundi.repository.AuthorityRepository;
import ibs124.gundi.service.auth.AuthorityInitializingService;

@Service
class AuthorityInitializingServiceImpl implements AuthorityInitializingService {

    private final AuthorityRepository authorityRepository;
    private final AuthorityMapper authorityMapper;

    public AuthorityInitializingServiceImpl(
            AuthorityRepository roleRepository,
            AuthorityMapper roleMapper) {
        this.authorityRepository = roleRepository;
        this.authorityMapper = roleMapper;
    }

    @Override
    public Authority[] initialize() {
        this.authorityRepository.deleteAll();

        List<AuthorityEntity> authList = this.createNew();

        authList = this.authorityRepository.saveAll(authList);

        return this.prepareResponse(authList);
    }

    private Authority[] prepareResponse(List<AuthorityEntity> args) {
        return this.authorityMapper
                .mapToApplicationModelAll(args)
                .stream()
                .toArray(Authority[]::new);
    }

    private List<AuthorityEntity> createNew() {
        return Arrays
                .stream(AuthorityConfig.DEFAULT_AUTHORITIES)
                .map(x -> new AuthorityEntity(x.getAuthority()))
                .toList();
    }

}
