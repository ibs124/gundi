package ibs124.gundi.service.auth.impl;

import java.time.Instant;

import org.springframework.stereotype.Service;

import ibs124.gundi.model.dto.auth.TokenConsumeRequest;
import ibs124.gundi.model.dto.auth.TokenDto;
import ibs124.gundi.model.entity.EmailEntity;
import ibs124.gundi.model.entity.UserEntity;
import ibs124.gundi.model.entity.VerificationTokenEntity;
import ibs124.gundi.repository.EmailRepository;
import ibs124.gundi.repository.VerificationTokenRepository;
import ibs124.gundi.service.auth.VerificationTokenConsumingService;
import jakarta.transaction.Transactional;
import jakarta.validation.Validator;

@Service
class VerificationTokenConsumingServiceImpl
        extends AbstractTokenConsumingService<VerificationTokenEntity>
        implements VerificationTokenConsumingService {

    private final EmailRepository emailRepository;

    public VerificationTokenConsumingServiceImpl(
            VerificationTokenRepository tokenRepository,
            Validator validator,
            EmailRepository emailRepository) {

        super(tokenRepository, validator);

        this.emailRepository = emailRepository;
    }

    @Transactional
    @Override
    public TokenDto consume(TokenConsumeRequest request) {
        VerificationTokenEntity token = super.consumeAbstract(request);

        if (token == null) {
            return null;
        }

        UserEntity user = token.getUser();
        user.setLastMfaVerifiedAt(Instant.now());

        if (user.getLastMfaVerifiedAt() == null) {
            this.verifyNewUser(user);
        }

        return super.mapToDto(token, user.getPrimaryEmail());
    }

    private void verifyNewUser(UserEntity user) {
        EmailEntity email = new EmailEntity(user, user.getPrimaryEmail());

        email.setLastVerifiedAt(user.getLastMfaVerifiedAt());

        email = this.emailRepository.save(email);
    }

}
