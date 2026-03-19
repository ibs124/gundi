package ibs124.gundi.service.auth.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import ibs124.gundi.mapper.AuthorityMapper;
import ibs124.gundi.model.application.Authority;
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
    public Collection<? extends Authority> create(Collection<? extends Authority> args) {
        List<String> names = args
                .stream()
                .map(x -> x.getAuthority())
                .distinct()
                .toList();

        List<AuthorityEntity> authorities = this.authorityRepository
                .findByNameIn(names);

        if (!authorities.isEmpty()) {
            return this.authorityMapper.mapToApplicationModelAll(authorities);
        }

        authorities = names
                .stream()
                .map(x -> new AuthorityEntity(x))
                .toList();

        authorities = this.authorityRepository.saveAll(authorities);

        return this.authorityMapper.mapToApplicationModelAll(authorities);
    }
}
