package ibs124.gundi.service.auth.domain;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import org.springframework.stereotype.Service;

import ibs124.gundi.config.PropertyConfig;
import ibs124.gundi.exception.ResourceCreatingException;
import ibs124.gundi.exception.ResourceReadingException;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.model.domain.VerificationToken;
import ibs124.gundi.repository.UserRepository;
import ibs124.gundi.repository.VerificationTokenRepository;

@Service
class TokenCreateDomainServiceImpl implements TokenCreateDomainService {

    private final VerificationTokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final PropertyConfig config;

    public TokenCreateDomainServiceImpl(VerificationTokenRepository tokenRepository,
            UserRepository userRepository, PropertyConfig config) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.config = config;
    }

    @Override
    public String createByUserId(long id) {
        try {
            VerificationToken token = new VerificationToken();

            token.setOwner(this.getUser(id));

            token.setExpiresAt(this.getExpiration());

            token.setValue(getValue());

            token = this.tokenRepository.save(token);

            return token.getValue();
        } catch (Exception e) {
            throw new ResourceCreatingException();
        }
    }

    private User getUser(long id) {
        return this.userRepository
                .findById(id)
                .orElseThrow(() -> new ResourceReadingException());
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
