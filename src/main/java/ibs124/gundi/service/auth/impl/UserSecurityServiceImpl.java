package ibs124.gundi.service.auth.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ibs124.gundi.config.PropertyConfiguration;
import ibs124.gundi.service.auth.UserSecurityService;

@Service
public class UserSecurityServiceImpl implements UserSecurityService {

    private final PasswordEncoder passwordEncoder;
    private final PropertyConfiguration config;

    public UserSecurityServiceImpl(
            PasswordEncoder passwordEncoder,
            PropertyConfiguration config) {
        this.passwordEncoder = passwordEncoder;
        this.config = config;
    }

    @Override
    public Instant computeNewUserAccountExpiration() {
        return Instant
                .now()
                .plus(
                        this.config.newUser().timeframeHours(), ChronoUnit.HOURS);
    }

    @Override
    public String encodePassword(String raw) {
        return this.passwordEncoder.encode(raw);
    }

}
