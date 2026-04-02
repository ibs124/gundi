package ibs124.gundi.service.auth.impl;

import org.springframework.stereotype.Service;

import ibs124.gundi.common.token_generator.TokenGenerator;
import ibs124.gundi.common.token_generator.model.TokenGenerateResponse;
import ibs124.gundi.constant.Env;
import ibs124.gundi.model.dto.auth.TokenDto;
import ibs124.gundi.model.entity.PasswordResetTokenEntity;
import ibs124.gundi.model.entity.UserEntity;
import ibs124.gundi.repository.PasswordResetTokenRepository;
import ibs124.gundi.repository.UserRepository;
import ibs124.gundi.service.auth.PasswordResetTokenCreatingService;
import jakarta.validation.Validator;

@Service
class PasswordResetTokenCreatingServiceImpl
        extends AbstractTokenCreatingService<PasswordResetTokenEntity>
        implements PasswordResetTokenCreatingService {

    private final PasswordResetTokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final TokenGenerator tokenGenerator;

    public PasswordResetTokenCreatingServiceImpl(
            Validator validator,
            PasswordResetTokenRepository tokenRepository,
            UserRepository userRepository,
            TokenGenerator tokenGenerator) {

        super(validator, tokenRepository);

        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.tokenGenerator = tokenGenerator;
    }

    @Override
    PasswordResetTokenEntity construct(UserEntity user) {
        return new PasswordResetTokenEntity(user);
    }

    @Override
    TokenDto generateToken() {
        TokenGenerateResponse response = this.tokenGenerator
                .generateBySecret(Env.REQUEST_KEY_PASSWORD_RESET);
        return new TokenDto(response.getSecret(), response.getExpiresAt());
    }

    @Override
    public TokenDto create(String username) {
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
