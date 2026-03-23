package ibs124.gundi.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ibs124.gundi.constant.Messages;
import ibs124.gundi.mapper.UserMapper;
import ibs124.gundi.repository.UserRepository;

@Service
class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDetailsServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository
                .findByUsernameOrPrimaryEmail(username, username)
                .map(x -> this.userMapper.mapToSecurityModel(x))
                .orElseThrow(() -> new UsernameNotFoundException(Messages.USER_NOT_FOUND));
    }

}
