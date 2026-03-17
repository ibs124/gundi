package ibs124.gundi.service.auth.impl;

import java.time.Instant;

import org.springframework.stereotype.Service;

import ibs124.gundi.model.application.Role;
import ibs124.gundi.model.application.dto.TokenDto;
import ibs124.gundi.model.persistence.AuthorityEntity;
import ibs124.gundi.model.persistence.EmailEntity;
import ibs124.gundi.model.persistence.UserEntity;
import ibs124.gundi.model.persistence.VerificationTokenEntity;
import ibs124.gundi.repository.EmailRepository;
import ibs124.gundi.repository.AuthorityRepository;
import ibs124.gundi.repository.VerificationTokenRepository;
import ibs124.gundi.service.auth.VerificationService;
import jakarta.transaction.Transactional;
import jakarta.validation.Validator;

@Service
class VerificationServiceImpl implements VerificationService {

    private final Validator validator;
    private final VerificationTokenRepository tokenRepository;
    private final AuthorityRepository roleRepository;
    private final EmailRepository emailRepository;

    public VerificationServiceImpl(
            Validator validator,
            VerificationTokenRepository tokenRepository,
            AuthorityRepository roleRepository,
            EmailRepository emailRepository) {
        this.validator = validator;
        this.tokenRepository = tokenRepository;
        this.roleRepository = roleRepository;
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
        user.setLastVerifiedAt(Instant.now());

        if (!user.isEnabled() && user.getLastVerifiedAt() == null) {
            this.verifyNewUser(user);
        }

        TokenDto response = new TokenDto(
                user.getPrimaryEmail(),
                token.getSecret(),
                token.getExpiresAt());

        return response;
    }

    private VerificationTokenEntity consumeTokenBySecret(String secret) {
        VerificationTokenEntity token = this.tokenRepository
                .findBySecret(secret)
                .orElse(null);

        if (token == null) {
            return null;
        }

        boolean isTokenValid = this.validator.validate(token).isEmpty();

        if (!isTokenValid) {
            return null;
        }

        this.tokenRepository.delete(token);

        return token;
    }

    private void verifyNewUser(UserEntity user) {
        AuthorityEntity userRole = this.roleRepository
                .getReferenceById(Role.USER.ordinal() + 1L);

        user.addAuthority(userRole);

        user.setEnabled(true);

        EmailEntity email = new EmailEntity(user, user.getPrimaryEmail());

        email.setLastVerifiedAt(user.getLastVerifiedAt());

        email = this.emailRepository.save(email);
    }

}
