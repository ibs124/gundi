package ibs124.gundi.service.auth.impl;

import ibs124.gundi.common.token_generator.TokenGenerator;
import ibs124.gundi.common.token_generator.model.TokenGenerateResponse;
import ibs124.gundi.model.dto.auth.TokenContract;
import ibs124.gundi.model.dto.auth.TokenDto;
import ibs124.gundi.model.entity.AbstractTokenEntity;
import ibs124.gundi.model.entity.UserEntity;
import ibs124.gundi.repository.AbstractTokenRepository;
import jakarta.validation.Validator;

abstract class AbstractTokenCreatingService<T extends AbstractTokenEntity> {

    private final Validator validator;
    TokenGenerator tokenGenerator;
    private final AbstractTokenRepository<T> tokenRepository;

    public AbstractTokenCreatingService(
            Validator validator,
            TokenGenerator tokenGenerator,
            AbstractTokenRepository<T> tokenRepository) {

        this.validator = validator;
        this.tokenGenerator = tokenGenerator;
        this.tokenRepository = tokenRepository;
    }

    abstract String getTokenGenerationSecret();

    abstract T getNewToken(UserEntity user);

    protected TokenContract respondWithTokenRepair(T token) {
        if (token == null) {
            return null;
        }

        boolean tokenIsValid = this.validator.validate(token).isEmpty();

        if (!tokenIsValid) {
            token = this.refreshToken(token);
        }

        return this.mapToDto(token, token.getUser().getPrimaryEmail());
    }

    protected TokenContract respondWithNewToken(UserEntity user) {
        return this.mapToDto(
                this.createNewToken(user),
                user.getPrimaryEmail());
    }

    protected T createNewToken(UserEntity user) {
        if (user == null) {
            return null;
        }

        T token = this.getNewToken(user);

        token.setUser(user);

        token = this.refreshToken(token);

        return token;
    }

    protected T refreshToken(T token) {
        String secret = this.getTokenGenerationSecret();

        TokenGenerateResponse response = this.tokenGenerator.generateBySecret(secret);

        while (this.tokenRepository.existsBySecret(secret)) {
            response = this.tokenGenerator.generateBySecret(secret);
        }

        token.setSecret(response.getSecret());
        token.setExpiresAt(response.getExpiresAt());

        token = this.tokenRepository.save(token);

        return token;
    }

    protected TokenContract mapToDto(T token, String username) {
        return new TokenDto(username, token.getSecret(), token.getExpiresAt());
    }

}
