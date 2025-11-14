package ibs124.gundi.service.user;

import java.util.Optional;

import org.springframework.stereotype.Service;

import ibs124.gundi.mapper.UserMapper;
import ibs124.gundi.model.dto.UserDetailsImpl;
import ibs124.gundi.repository.UserRepository;

@Service
class UserReadingServiceImpl implements UserReadingService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserReadingServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<UserDetailsImpl> findByUsernameOrEmail(String usernameOrEmail) {
        return this.userRepository
                .findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .map(x -> this.userMapper.toSecurityModel(x));
    }

}
