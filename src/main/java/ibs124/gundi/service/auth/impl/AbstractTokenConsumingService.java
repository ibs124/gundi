package ibs124.gundi.service.auth.impl;

import ibs124.gundi.model.entity.AbstractTokenEntity;
import ibs124.gundi.repository.AbstractTokenRepository;
import jakarta.validation.Validator;

public abstract class AbstractTokenConsumingService<T extends AbstractTokenEntity> {

    private final AbstractTokenRepository<T> tokenRepository;
    private final Validator validator;

    public AbstractTokenConsumingService(
            AbstractTokenRepository<T> tokenRepository,
            Validator validator) {

        this.tokenRepository = tokenRepository;
        this.validator = validator;
    }

    protected T consumeTokenBySecret(String secret) {
        T token = this.tokenRepository
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

}
