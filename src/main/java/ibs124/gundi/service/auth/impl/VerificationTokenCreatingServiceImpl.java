package ibs124.gundi.service.auth.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import org.springframework.stereotype.Service;

import ibs124.gundi.configuration.PropertyConfig;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.model.domain.VerificationToken;
import ibs124.gundi.model.enumm.VerificationType;
import ibs124.gundi.repository.UserRepository;
import ibs124.gundi.repository.VerificationTokenRepository;
import ibs124.gundi.service.auth.VerificationTokenCreatingService;

@Service
class VerificationTokenCreatingServiceImpl implements VerificationTokenCreatingService {

    private final VerificationTokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final PropertyConfig config;

    public VerificationTokenCreatingServiceImpl(
            VerificationTokenRepository tokenRepository,
            UserRepository userRepository,
            PropertyConfig propertyConfig) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.config = propertyConfig;
    }

    @Override
    public String createNewUserVerificationTokenByUserId(Long userId) {
        User user = this.userRepository.getReferenceById(userId);

        VerificationToken token = new VerificationToken();

        token.setOwner(user);
        token.setType(VerificationType.NEW_USER);
        token.setValue(this.getValueByType(token.getType()));
        token.setExpiresAt(this.getExpiration());

        token = this.tokenRepository.save(token);

        return token.getValue();
    }

    private Instant getExpiration() {
        int minutes = this.config.newUser().tokenExpirationMinutes();
        return Instant
                .now()
                .plus(minutes, ChronoUnit.MINUTES);
    }

    private String getValueByType(VerificationType type) {
        String value = UUID.randomUUID().toString();

        while (this.tokenRepository.existsByValue(value)) {
            value = UUID.randomUUID().toString();
        }

        return value;
    }

}
