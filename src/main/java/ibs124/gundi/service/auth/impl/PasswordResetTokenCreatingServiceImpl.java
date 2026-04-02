package ibs124.gundi.service.auth.impl;

import org.springframework.stereotype.Service;

import ibs124.gundi.common.token_generator.TokenGenerator;
import ibs124.gundi.common.token_generator.model.TokenGenerateRequest;
import ibs124.gundi.common.token_generator.model.TokenGenerateResponse;
import ibs124.gundi.model.dto.auth.TokenDto;
import ibs124.gundi.model.entity.PasswordResetTokenEntity;
import ibs124.gundi.model.entity.UserEntity;
import ibs124.gundi.repository.PasswordResetTokenRepository;
import ibs124.gundi.repository.UserRepository;
import ibs124.gundi.service.auth.PasswordResetTokenGeneratingService;
import ibs124.gundi.service.auth.PasswordResetTokenCreatingService;
import jakarta.validation.Validator;

@Service
class PasswordResetTokenCreatingServiceImpl
        extends AbstractTokenCreatingService<PasswordResetTokenEntity>
        implements PasswordResetTokenCreatingService {

    private final PasswordResetTokenRepository tokenRepository;
    private final PasswordResetTokenGeneratingService tokenCreatingService;
    private final UserRepository userRepository;
    private final TokenGenerator tokenGenerator;

    public PasswordResetTokenCreatingServiceImpl(
            Validator validator,
            PasswordResetTokenRepository tokenRepository,
            PasswordResetTokenGeneratingService tokenCreatingService,
            UserRepository userRepository,
            TokenGenerator tokenGenerator) {

        super(validator, tokenRepository);

        this.tokenRepository = tokenRepository;
        this.tokenCreatingService = tokenCreatingService;
        this.userRepository = userRepository;
        this.tokenGenerator = tokenGenerator;
    }

    @Override
    PasswordResetTokenEntity construct(UserEntity user) {
        return new PasswordResetTokenEntity(user);
    }

    @Override
    TokenDto generateToken() {
        TokenGenerateRequest request = this.tokenGenerator
                .getConfiguration()
                .getRecoveryTokenConfiguration();

        TokenGenerateResponse response = this.tokenGenerator.generate(request);

        return new TokenDto(response.getSecret(), response.getExpiresAt());
    }

    @Override
    public TokenDto createByUsername(String username) {
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
