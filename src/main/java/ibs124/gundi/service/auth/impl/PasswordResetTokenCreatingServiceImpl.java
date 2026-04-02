package ibs124.gundi.service.auth.impl;

import org.springframework.stereotype.Service;

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

    public PasswordResetTokenCreatingServiceImpl(
            Validator validator,
            PasswordResetTokenRepository tokenRepository,
            PasswordResetTokenGeneratingService tokenCreatingService,
            UserRepository userRepository) {

        super(validator, tokenRepository);

        this.tokenRepository = tokenRepository;
        this.tokenCreatingService = tokenCreatingService;
        this.userRepository = userRepository;
    }

    @Override
    PasswordResetTokenEntity construct(UserEntity user) {
        return new PasswordResetTokenEntity(user);
    }

    @Override
    TokenDto generateToken() {
        return this.tokenCreatingService.generatePasswordResetToken();
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
