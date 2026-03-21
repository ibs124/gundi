package ibs124.gundi.service.auth.impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
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

@Service
class UserCreatingServiceImpl implements UserCreatingService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    public UserCreatingServiceImpl(
            UserMapper userMapper,
            UserRepository userRepository,
            AuthorityRepository authorityRepository,
            PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto create(UserCreateDto request) {
        UserEntity user = this.userMapper
                .mapToPersistenceModel(request);

        user = this.assignAuthorities(user);

        user = this.setDefaultUserState(user);

        user = this.userRepository.save(user);

        return this.userMapper.mapToApplicationModel(user);
    }

    private UserEntity assignAuthorities(UserEntity user) {
        List<AuthorityEntity> authorities = this.authorityRepository
                .findByNameIn(AuthorityConfig.ROLE_USER.getAuthority());

        user.addAuthority(authorities.get(0));

        return user;
    }

    private UserEntity setDefaultUserState(UserEntity user) {
        String encodedPassword = this.passwordEncoder
                .encode(user.getPassword());

        user.setPassword(encodedPassword);

        user.setEnabled(true);

        return user;
    }

}