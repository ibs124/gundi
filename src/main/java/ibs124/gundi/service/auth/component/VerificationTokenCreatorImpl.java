package ibs124.gundi.service.auth.component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import org.springframework.stereotype.Service;

import ibs124.gundi.config.PropertyConfig;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.model.domain.VerificationToken;
import ibs124.gundi.repository.VerificationTokenRepository;

@Service
class VerificationTokenCreatorImpl implements VerificationTokenCreator {

    private final VerificationTokenRepository tokenRepository;
    private final PropertyConfig config;

    public VerificationTokenCreatorImpl(
            VerificationTokenRepository tokenRepository,
            PropertyConfig config) {
        this.tokenRepository = tokenRepository;
        this.config = config;
    }

    @Override
    public VerificationToken createByUser(User user) {
        VerificationToken token = new VerificationToken();
        token.setOwner(user);
        token.setValue(this.getValue());
        token.setExpiresAt(this.getExpiration());

        return this.tokenRepository.save(token);
    }

    private Instant getExpiration() {
        return Instant
                .now()
                .plus(this.config.tokenExpirationMinutes(), ChronoUnit.MINUTES);
    }

    private String getValue() {
        String value = UUID.randomUUID().toString();

        while (this.tokenRepository.existsByValue(value)) {
            value = UUID.randomUUID().toString();
        }

        return value;
    }
}
