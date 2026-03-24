package ibs124.gundi.service.sample.impl;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ibs124.gundi.config.AuthorityConfig;
import ibs124.gundi.model.entity.AuthorityEntity;
import ibs124.gundi.model.entity.UserEntity;
import ibs124.gundi.repository.AuthorityRepository;
import jakarta.validation.constraints.NotBlank;

@Service
class AuthoritySeeder {

    private final AuthorityRepository authorityRepository;

    public AuthoritySeeder(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public List<UserEntity> seedAuthorities(List<UserEntity> users) {
        Map<String, AuthorityEntity> authorities = this.seedAuthorities();

        AuthorityEntity rootAuth = authorities
                .get(AuthorityConfig.ROLE_ROOT.getAuthority());

        users.get(0).addAuthority(rootAuth);

        AuthorityEntity adminAuth = authorities
                .get(AuthorityConfig.ROLE_ADMIN.getAuthority());

        for (int i = 0; i < Config.ADMINS_COUNT; i++) {
            users.get(i).addAuthority(adminAuth);
        }

        AuthorityEntity userAuth = authorities
                .get(AuthorityConfig.ROLE_USER.getAuthority());

        AuthorityEntity sampleAuth = authorities
                .get(Config.SAMPLE_FACTOR.getAuthority());

        for (int i = 0; i < users.size(); i++) {
            users.get(i).addAuthority(userAuth);
            users.get(i).addAuthority(sampleAuth);
        }

        return users;
    }

    private Map<String, AuthorityEntity> seedAuthorities() {
        Map<@NotBlank String, AuthorityEntity> map = this.authorityRepository
                .findAll()
                .stream()
                .collect(Collectors
                        .toMap(AuthorityEntity::getName, Function.identity()));

        String sampleAuthority = Config.SAMPLE_FACTOR.getAuthority();

        if (map.containsKey(sampleAuthority)) {
            return map;
        }

        AuthorityEntity sampleAuthorityEntity = this.authorityRepository
                .save(new AuthorityEntity(sampleAuthority));

        map.put(sampleAuthority, sampleAuthorityEntity);

        return map;
    }

}
