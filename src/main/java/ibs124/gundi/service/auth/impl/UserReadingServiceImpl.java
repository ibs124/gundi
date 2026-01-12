package ibs124.gundi.service.auth.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ibs124.gundi.config.MessageConfig;
import ibs124.gundi.mapper.UserMapper;
import ibs124.gundi.repository.UserRepository;
import ibs124.gundi.service.auth.UserReadingService;

@Service
class UserReadingServiceImpl implements UserReadingService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserReadingServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository
                .findByUsernameOrPrimaryEmail(username, username)
                .map(x -> this.userMapper.toSecurityModel(x))
                .orElseThrow(() -> new UsernameNotFoundException(MessageConfig.USER_NOT_FOUND));
    }

    @Override
    public boolean existsByUsername(String username) {
        return this.userRepository.existsByUsername(username);
    }

}
