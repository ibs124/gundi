package ibs124.gundi.service.auth.token.impl;

import org.springframework.stereotype.Service;

import ibs124.gundi.repository.PasswordResetTokenRepository;
import ibs124.gundi.service.auth.user.PasswordResetValidationService;
import jakarta.validation.Validator;

@Service
class PasswordResetValidationServiceImpl implements PasswordResetValidationService {

    private final Validator validator;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    public PasswordResetValidationServiceImpl(Validator validator,
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
