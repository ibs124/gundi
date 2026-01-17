package ibs124.gundi.service.auth.impl;

import java.time.Instant;

import org.springframework.stereotype.Service;

import ibs124.gundi.model.domain.User;
import ibs124.gundi.model.domain.VerificationToken;
import ibs124.gundi.repository.UserRepository;
import ibs124.gundi.repository.VerificationTokenRepository;
import ibs124.gundi.service.auth.UserVerifyingService;
import jakarta.validation.Validator;

@Service
class UserVerifyingServiceImpl implements UserVerifyingService {

    private final VerificationTokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final Validator validator;

    public UserVerifyingServiceImpl(
            VerificationTokenRepository tokenRepository,
            UserRepository userRepository,
            Validator validator) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.validator = validator;
    }

    @Override
    public boolean verifyNewUserVerificationToken(String request) {
        VerificationToken token = this.tokenRepository
                .findByValueAndExpiresAtBefore(request, Instant.now())
                .filter(x -> this.validator.validate(x).isEmpty())
                .orElse(null);

        if (token == null) {
            return false;
        }

        this.tokenRepository.delete(token);

        User user = token.getOwner();

        user.setIsEnabled(true);

        user = this.userRepository.save(user);

        return true;
    }

}
