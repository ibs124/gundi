package ibs124.gundi.service.auth.user.impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ibs124.gundi.config.AuthorityConfig;
import ibs124.gundi.mapper.UserMapper;
import ibs124.gundi.model.dto.auth.UserDto;
import ibs124.gundi.model.dto.auth.UserRegisterDto;
import ibs124.gundi.model.entity.AuthorityEntity;
import ibs124.gundi.model.entity.UserEntity;
import ibs124.gundi.repository.AuthorityRepository;
import ibs124.gundi.repository.UserRepository;
import ibs124.gundi.service.auth.user.RegistrationService;

@Service
class RegistrationServiceImpl implements RegistrationService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationServiceImpl(
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
    public UserDto register(UserRegisterDto request) {
        UserEntity user = this.userMapper
                .mapToEntity(request);

        user = this.assignAuthorities(user);

        user = this.setDefaultUserState(user);

        user = this.userRepository.save(user);

        return this.userMapper.mapToDto(user);
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