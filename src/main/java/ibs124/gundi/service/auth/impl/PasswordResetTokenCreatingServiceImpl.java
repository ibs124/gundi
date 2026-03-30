package ibs124.gundi.service.auth.impl;

import org.springframework.stereotype.Service;

import ibs124.gundi.model.dto.auth.TokenDto;
import ibs124.gundi.model.entity.AbstractTokenEntity;
import ibs124.gundi.model.entity.PasswordResetTokenEntity;
import ibs124.gundi.model.entity.UserEntity;
import ibs124.gundi.repository.PasswordResetTokenRepository;
import ibs124.gundi.repository.UserRepository;
import ibs124.gundi.service.auth.PasswordResetTokenConfiguringService;
import ibs124.gundi.service.auth.PasswordResetTokenCreatingService;
import jakarta.validation.Validator;

@Service
class PasswordResetTokenCreatingServiceImpl implements PasswordResetTokenCreatingService {

    private final Validator validator;
    private final PasswordResetTokenRepository tokenRepository;
    private final PasswordResetTokenConfiguringService tokenCreatingService;
    private final UserRepository userRepository;

    public PasswordResetTokenCreatingServiceImpl(
            Validator validator,
            PasswordResetTokenRepository tokenRepository,
            PasswordResetTokenConfiguringService tokenCreatingService,
            UserRepository userRepository) {
        this.validator = validator;
        this.tokenRepository = tokenRepository;
        this.tokenCreatingService = tokenCreatingService;
        this.userRepository = userRepository;
    }

    @Override
    public TokenDto issueByUsername(String username) {
        PasswordResetTokenEntity cache = this.tokenRepository
                .findByUserUsernameOrUserPrimaryEmail(username, username)
                .orElse(null);

        if (cache == null) {
            return this.createNewToken(username);
        }

        boolean cacheIsValid = this.validator.validate(cache).isEmpty();

        return cacheIsValid ? this.mapValidEntityToDto(cache) : this.refreshToken(cache);
    }

    private TokenDto createNewToken(String username) {
        UserEntity user = this.userRepository
                .findByUsernameOrPrimaryEmail(username, username)
                .orElse(null);

        if (user == null) {
            return null;
        }

        PasswordResetTokenEntity newToken = new PasswordResetTokenEntity(user);

        return this.refreshToken(newToken);
    }

    private TokenDto refreshToken(PasswordResetTokenEntity token) {
        TokenDto tokenDto = this.tokenCreatingService.configurePasswordResetToken();

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
