package ibs124.gundi.service.auth.impl;

import ibs124.gundi.model.dto.auth.TokenConsumeRequest;
import ibs124.gundi.model.dto.auth.TokenContract;
import ibs124.gundi.model.dto.auth.TokenDto;
import ibs124.gundi.model.entity.AbstractTokenEntity;
import ibs124.gundi.repository.AbstractTokenRepository;
import ibs124.gundi.service.auth.AbstractTokenConsumingService;
import jakarta.validation.Validator;

public abstract class AbstractTokenConsumingServiceImpl<T extends AbstractTokenEntity, R extends TokenConsumeRequest>
        implements AbstractTokenConsumingService<R> {

    private final AbstractTokenRepository<T> tokenRepository;
    private final Validator validator;

    public AbstractTokenConsumingServiceImpl(
            AbstractTokenRepository<T> tokenRepository,
            Validator validator) {

        this.tokenRepository = tokenRepository;
        this.validator = validator;
    }

    protected T consumeAbstract(TokenConsumeRequest request) {

        if (this.requestIsValid(request) == false) {
            return null;
        }

        T token = this.tokenRepository
                .findBySecret(request.getSecret())
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

    protected TokenContract mapToDto(T token, String username) {
        return new TokenDto(username, token.getSecret(), token.getExpiresAt());
    }

    private boolean requestIsValid(TokenConsumeRequest request) {
        return request != null
                && request.getSecret() != null
                && this.validator.validate(request).isEmpty();
    }

}
