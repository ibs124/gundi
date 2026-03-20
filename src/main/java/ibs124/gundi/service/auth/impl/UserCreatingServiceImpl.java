package ibs124.gundi.service.auth.impl;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import ibs124.gundi.config.AuthorityConfig;
import ibs124.gundi.mapper.UserMapper;
import ibs124.gundi.model.application.dto.UserCreateDto;
import ibs124.gundi.model.application.dto.UserDto;
import ibs124.gundi.model.persistence.AuthorityEntity;
import ibs124.gundi.model.persistence.UserEntity;
import ibs124.gundi.repository.AuthorityRepository;
import ibs124.gundi.repository.UserRepository;
import ibs124.gundi.service.auth.UserCreatingService;
import ibs124.gundi.service.auth.UserConfiguringService;

@Service
class UserCreatingServiceImpl implements UserCreatingService {

    private final UserMapper userMapper;
    private final UserConfiguringService userConfigService;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    public UserCreatingServiceImpl(UserMapper userMapper, UserConfiguringService userConfigService,
            UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userMapper = userMapper;
        this.userConfigService = userConfigService;
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public UserDto create(UserCreateDto request) {
        UserEntity user = this.userMapper
                .mapToPersistenceModel(request);

        user = this.assignAuthorities(user);

        user = this.configure(user);

        user = this.userRepository.save(user);

        return this.userMapper.mapToApplicationModel(user);
    }

    private UserEntity assignAuthorities(UserEntity user) {
        List<String> defaultNewUserAuthorities = List.of(
                AuthorityConfig.ROLE_USER.getAuthority(),
                AuthorityConfig.FACTOR_NEW_USER.getAuthority());

        List<AuthorityEntity> authorities = this.authorityRepository
                .findByNameIn(defaultNewUserAuthorities);

        user.addAuthority(authorities.get(0));
        user.addAuthority(authorities.get(1));

        return user;
    }

    private UserEntity configure(UserEntity user) {
        user.setPassword(
                this.userConfigService
                        .encodePassword(user.getPassword()));

        user.setAccountExpiresAt(
                this.userConfigService.getAccountExpiration());

        user.setMfaEnabledAt(Instant.now());

        user.setEnabled(true);

        return user;
    }

}