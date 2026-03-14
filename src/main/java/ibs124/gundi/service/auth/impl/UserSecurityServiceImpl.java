package ibs124.gundi.service.auth.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ibs124.gundi.config.PropertyConfig;
import ibs124.gundi.service.auth.UserConfigService;

@Service
public class UserSecurityServiceImpl implements UserConfigService {

    private final PasswordEncoder passwordEncoder;
    private final PropertyConfig config;

    public UserSecurityServiceImpl(
            PasswordEncoder passwordEncoder,
            PropertyConfig config) {
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
