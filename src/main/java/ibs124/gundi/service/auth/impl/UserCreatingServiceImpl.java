package ibs124.gundi.service.auth.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ibs124.gundi.mapper.UserMapper;
import ibs124.gundi.model.application.UserCreateDto;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.repository.UserRepository;
import ibs124.gundi.service.auth.UserCreatingService;

@Service
class UserCreatingServiceImpl implements UserCreatingService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserCreatingServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public Long createUser(UserCreateDto request) {
        String encodedPassword = this.passwordEncoder
                .encode(request.password());

        User user = this.userMapper
                .mapToDomainModel(request);

        user.setPassword(encodedPassword);

        user = this.userRepository.save(user);

        return user.getId();
    }

}
