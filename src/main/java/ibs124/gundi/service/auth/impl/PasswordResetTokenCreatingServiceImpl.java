package ibs124.gundi.service.auth.impl;

import org.springframework.stereotype.Service;

import ibs124.gundi.model.dto.auth.TokenDto;
import ibs124.gundi.model.entity.AbstractTokenEntity;
import ibs124.gundi.model.entity.PasswordResetTokenEntity;
import ibs124.gundi.model.entity.UserEntity;
import ibs124.gundi.repository.PasswordResetTokenRepository;
import ibs124.gundi.repository.UserRepository;
import ibs124.gundi.service.auth.PasswordResetTokenGeneratingService;
import ibs124.gundi.service.auth.PasswordResetTokenCreatingService;
import jakarta.validation.Validator;

@Service
class PasswordResetTokenCreatingServiceImpl implements PasswordResetTokenCreatingService {

    private final Validator validator;
    private final PasswordResetTokenRepository tokenRepository;
    private final PasswordResetTokenGeneratingService tokenCreatingService;
    private final UserRepository userRepository;

    public PasswordResetTokenCreatingServiceImpl(
            Validator validator,
            PasswordResetTokenRepository tokenRepository,
            PasswordResetTokenGeneratingService tokenCreatingService,
            UserRepository userRepository) {
        this.validator = validator;
        this.tokenRepository = tokenRepository;
        this.tokenCreatingService = tokenCreatingService;
        this.userRepository = userRepository;
    }

    @Override
    public TokenDto createByUsername(String username) {
        PasswordResetTokenEntity token = this.tokenRepository
                .findByUserUsernameOrUserPrimaryEmail(username, username)
                .orElse(null);

        if (token == null) {
            return this.createNewToken(username);
        }

        boolean cacheIsValid = this.validator.validate(token).isEmpty();

        return cacheIsValid ? this.mapValidEntityToDto(token) : this.refreshToken(token);
    }

    private TokenDto createNewToken(String username) {
        UserEntity user = this.userRepository
                .findByUsernameOrPrimaryEmail(username, username)
                .orElse(null);

        if (user == null) {
            return null;
        }

        PasswordResetTokenEntity token = new PasswordResetTokenEntity();

        token.setUser(user);

        return this.refreshToken(token);
    }

    private TokenDto refreshToken(PasswordResetTokenEntity token) {
        TokenDto tokenDto = this.tokenCreatingService.generatePasswordResetToken();

        token.setSecret(tokenDto.secret());
        token.setExpiresAt(tokenDto.expiresAt());

        token = this.tokenRepository.save(token);

        return this.mapValidEntityToDto(token);
    }

    private TokenDto mapValidEntityToDto(AbstractTokenEntity x) {
        return new TokenDto(
                x.getUser().getPrimaryEmail(), x.getSecret(), x.getExpiresAt());
    }

}
