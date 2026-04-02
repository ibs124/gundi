package ibs124.gundi.service.auth.impl;

import java.time.Instant;

import org.springframework.stereotype.Service;

import ibs124.gundi.model.dto.auth.TokenDto;
import ibs124.gundi.model.entity.EmailEntity;
import ibs124.gundi.model.entity.UserEntity;
import ibs124.gundi.model.entity.VerificationTokenEntity;
import ibs124.gundi.repository.EmailRepository;
import ibs124.gundi.repository.VerificationTokenRepository;
import ibs124.gundi.service.auth.VerificationService;
import jakarta.transaction.Transactional;
import jakarta.validation.Validator;

@Service
class VerificationServiceImpl
        extends AbstractTokenConsumingService<VerificationTokenEntity>
        implements VerificationService {

    private final EmailRepository emailRepository;

    public VerificationServiceImpl(
            VerificationTokenRepository tokenRepository,
            Validator validator,
            EmailRepository emailRepository) {

        super(tokenRepository, validator);

        this.emailRepository = emailRepository;
    }

    @Override
    @Transactional
    public TokenDto verifyBySecret(String request) {
        VerificationTokenEntity token = this.consumeTokenBySecret(request);

        if (token == null) {
            return null;
        }

        UserEntity user = token.getUser();
        user.setLastMfaVerifiedAt(Instant.now());

        if (user.getLastMfaVerifiedAt() == null) {
            this.verifyNewUser(user);
        }

        return new TokenDto(
                token.getUser().getUsername(), token.getSecret(), token.getExpiresAt());
    }

    private void verifyNewUser(UserEntity user) {
        EmailEntity email = new EmailEntity(user, user.getPrimaryEmail());

        email.setLastVerifiedAt(user.getLastMfaVerifiedAt());

        email = this.emailRepository.save(email);
    }

}
