package ibs124.gundi.service.auth.infrastructure;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ibs124.gundi.config.MessageConfig;
import ibs124.gundi.mapper.UserMapper;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.repository.EmailRepository;
import ibs124.gundi.repository.UserRepository;

@Service
class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final EmailRepository emailRepository;

    public UserDetailsServiceImpl(
            UserRepository userRepository,
            UserMapper userMapper, EmailRepository emailRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.emailRepository = emailRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = username.contains("@")
                ? this.emailRepository
                        .findByNameAndPrimaryTrueAndVerifiedAtIsNotNull(username)
                        .map(x -> x.getUser())
                : this.userRepository.findByUsername(username);

        return userOptional
                .map(x -> this.userMapper.mapToInfrastructureModel(x))
                .orElseThrow(() -> new UsernameNotFoundException(MessageConfig.USER_NOT_FOUND));
    }

}
