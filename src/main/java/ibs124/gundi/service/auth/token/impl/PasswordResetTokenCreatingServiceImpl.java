package ibs124.gundi.service.auth.token.impl;

import org.springframework.stereotype.Service;

import ibs124.gundi.common.token_generator.TokenGenerator;
import ibs124.gundi.constant.Env;
import ibs124.gundi.model.dto.auth.TokenContract;
import ibs124.gundi.model.dto.auth.TokenCreateRequest;
import ibs124.gundi.model.entity.PasswordResetTokenEntity;
import ibs124.gundi.model.entity.UserEntity;
import ibs124.gundi.repository.PasswordResetTokenRepository;
import ibs124.gundi.repository.UserRepository;
import jakarta.validation.Validator;

@Service
class PasswordResetTokenCreatingServiceImpl extends
        AbstractTokenCreatingServiceImpl<PasswordResetTokenEntity, TokenCreateRequest> {

    private final PasswordResetTokenRepository tokenRepository;
    private final UserRepository userRepository;

    public PasswordResetTokenCreatingServiceImpl(
            Validator validator,
            PasswordResetTokenRepository tokenRepository,
            UserRepository userRepository,
            TokenGenerator tokenGenerator) {

        super(validator, tokenGenerator, tokenRepository);

        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
    }

    @Override
    PasswordResetTokenEntity getNewToken(UserEntity user) {
        return new PasswordResetTokenEntity(user);
    }

    @Override
    String getTokenGenerationSecret() {
        return Env.REQUEST_KEY_PASSWORD_RESET;
    }

    @Override
    public TokenContract create(TokenCreateRequest request) {
        String username = request.getUsername();

        PasswordResetTokenEntity token = this.tokenRepository
                .findByUserUsernameOrUserPrimaryEmail(username, username)
                .orElse(null);

        if (token != null) {
            return super.respondWithTokenRepair(token);
        }

        UserEntity user = this.userRepository
                .findByUsernameOrPrimaryEmail(username, username)
                .orElse(null);

        return super.respondWithNewToken(user);
    }

}
