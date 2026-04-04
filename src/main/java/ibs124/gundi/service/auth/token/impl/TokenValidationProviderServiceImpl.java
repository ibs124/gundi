package ibs124.gundi.service.auth.token.impl;

import org.springframework.stereotype.Service;

import ibs124.gundi.model.dto.auth.PasswordResetDto;
import ibs124.gundi.repository.PasswordResetTokenRepository;
import ibs124.gundi.service.auth.token.TokenValidationProviderService;
import jakarta.validation.Validator;

@Service
class TokenValidationProviderServiceImpl implements
        TokenValidationProviderService<PasswordResetDto> {

    private final Validator validator;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    public TokenValidationProviderServiceImpl(Validator validator,
            PasswordResetTokenRepository passwordResetTokenRepository) {
        this.validator = validator;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
    }

    @Override
    public boolean isTokenValid(PasswordResetDto request) {
        if (request == null || request.secret() == null) {
            return false;
        }

        return this.passwordResetTokenRepository
                .findBySecret(request.secret())
                .filter(x -> x != null && this.validator.validate(x).isEmpty())
                .isPresent();
    }

}
