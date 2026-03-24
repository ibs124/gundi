package ibs124.gundi.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ibs124.gundi.constant.Messages;
import ibs124.gundi.mapper.UserMapper;
import ibs124.gundi.model.dto.UserLoginDetailsDto;
import ibs124.gundi.service.auth.UserLoginServiceService;

@Service
class UserDetailsServiceImpl implements UserDetailsService {

    private final UserLoginServiceService loginService;
    private final UserMapper userMapper;

    public UserDetailsServiceImpl(
            UserLoginServiceService loginService,
            UserMapper userMapper) {
        this.loginService = loginService;
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(
            String username) throws UsernameNotFoundException {

        UserLoginDetailsDto user = this.loginService.findByUsernameOrEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(Messages.USER_NOT_FOUND);
        }

        return this.userMapper.mapToSecurityModel(user);
    }

}
