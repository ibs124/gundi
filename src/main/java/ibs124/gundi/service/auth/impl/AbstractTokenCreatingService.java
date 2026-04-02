package ibs124.gundi.service.auth.impl;

import ibs124.gundi.model.dto.auth.TokenDto;
import ibs124.gundi.model.entity.AbstractTokenEntity;
import ibs124.gundi.model.entity.UserEntity;
import ibs124.gundi.repository.AbstractTokenRepository;
import jakarta.validation.Validator;

abstract class AbstractTokenCreatingService<T extends AbstractTokenEntity> {

    private final Validator validator;
    private final AbstractTokenRepository<T> tokenRepository;

    public AbstractTokenCreatingService(
            Validator validator,
            AbstractTokenRepository<T> tokenRepository) {
        this.validator = validator;
        this.tokenRepository = tokenRepository;
    }

    abstract TokenDto generateToken();

    abstract T construct(UserEntity user);

    protected TokenDto respondWithTokenRepair(T token) {
        if (token == null) {
            return null;
        }

        boolean tokenIsValid = this.validator.validate(token).isEmpty();

        if (!tokenIsValid) {
            token = this.refreshToken(token);
        }

        return this.mapToDto(token, token.getUser().getPrimaryEmail());
    }

    protected TokenDto respondWithNewToken(UserEntity user) {
        return this.mapToDto(
                this.createNewToken(user),
                user.getPrimaryEmail());
    }

    protected T createNewToken(UserEntity user) {
        if (user == null) {
            return null;
        }

        T token = this.construct(user);

        token.setUser(user);

        token = this.refreshToken(token);

        return token;
    }

    protected T refreshToken(T token) {
        TokenDto tokenDto = this.generateToken();

        token.setSecret(tokenDto.secret());
        token.setExpiresAt(tokenDto.expiresAt());

        token = this.tokenRepository.save(token);

        return token;
    }

    protected TokenDto mapToDto(T token, String username) {
        return new TokenDto(username, token.getSecret(), token.getExpiresAt());
    }

}
