package ibs124.gundi.service.auth.token.impl;

import org.springframework.stereotype.Service;

import ibs124.gundi.repository.PasswordResetTokenRepository;
import ibs124.gundi.service.auth.token.TokenValidationService;
import jakarta.validation.Validator;

@Service
class TokenValidationServiceImpl implements TokenValidationService {

    private final Validator validator;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    public TokenValidationServiceImpl(Validator validator,
            PasswordResetTokenRepository passwordResetTokenRepository) {
        this.validator = validator;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
    }

    @Override
    public boolean isPasswordResetTokenValid(String token) {
        if (token == null) {
            return false;
        }

        return this.passwordResetTokenRepository
                .findBySecret(token)
                .filter(x -> x != null && this.validator.validate(x).isEmpty())
                .isPresent();
    }

}
