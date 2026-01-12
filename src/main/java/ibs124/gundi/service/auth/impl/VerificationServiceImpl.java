package ibs124.gundi.service.auth.impl;

import java.time.Instant;

import org.springframework.stereotype.Service;

import ibs124.gundi.exception.ResourceUpdatingException;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.model.domain.VerificationToken;
import ibs124.gundi.repository.UserRepository;
import ibs124.gundi.repository.VerificationTokenRepository;
import ibs124.gundi.service.auth.VerificationService;
import jakarta.validation.Validator;

@Service
class VerificationServiceImpl implements VerificationService {

    private final VerificationTokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final Validator validator;

    public VerificationServiceImpl(
            VerificationTokenRepository tokenRepository,
            UserRepository userRepository,
            Validator validator) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.validator = validator;
    }

    @Override
    public boolean verifyNewUserByToken(String request) {
        try {
            VerificationToken token = this.tokenRepository
                    .findByValueAndExpiresAtBefore(request, Instant.now())
                    .filter(x -> this.validator.validate(x).isEmpty())
                    .orElseThrow(() -> new IllegalStateException());

            User user = token.getOwner();

            user.setIsEnabled(true);

            user = this.userRepository.save(user);

            this.tokenRepository.delete(token);

            return user.getIsEnabled();
        } catch (Exception e) {
            throw new ResourceUpdatingException();
        }
    }

}
