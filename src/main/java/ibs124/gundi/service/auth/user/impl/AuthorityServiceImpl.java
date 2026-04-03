package ibs124.gundi.service.auth.user.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import ibs124.gundi.config.AuthorityConfig;
import ibs124.gundi.mapper.AuthorityMapper;
import ibs124.gundi.model.dto.auth.AuthorityContract;
import ibs124.gundi.model.entity.AuthorityEntity;
import ibs124.gundi.repository.AuthorityRepository;
import ibs124.gundi.service.auth.user.AuthorityCreatingService;

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
        List<AuthorityEntity> authorities = this.authorityRepository.findAll();

        if (!authorities.isEmpty()) {
            return this.authorityMapper.mapToDtoAll(authorities);
        }

        this.authorityRepository.deleteAll();

        List<AuthorityEntity> authList = this.createNew();

        authList = this.authorityRepository.saveAll(authList);

        return this.authorityMapper.mapToDtoAll(authList);
    }

    private List<AuthorityEntity> createNew() {
        return Arrays
                .stream(AuthorityConfig.DEFAULT_AUTHORITIES)
                .map(x -> new AuthorityEntity(x.getAuthority()))
                .toList();
    }

}
