package ibs124.gundi.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ibs124.gundi.service.user.UserReadingService;

@Service
class UserDetailsServiceImpl implements UserDetailsService {

    private final UserReadingService userReadingService;

    public UserDetailsServiceImpl(UserReadingService userReadingService) {
        this.userReadingService = userReadingService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userReadingService
                .findByUsernameOrEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

}
