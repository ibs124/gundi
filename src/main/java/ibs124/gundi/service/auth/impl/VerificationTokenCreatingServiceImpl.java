package ibs124.gundi.service.auth.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import ibs124.gundi.exception.ResourceReadingException;
import ibs124.gundi.model.dto.auth.TokenDto;
import ibs124.gundi.model.entity.AbstractTokenEntity;
import ibs124.gundi.model.entity.UserEntity;
import ibs124.gundi.model.entity.VerificationTokenEntity;
import ibs124.gundi.repository.UserRepository;
import ibs124.gundi.repository.VerificationTokenRepository;
import ibs124.gundi.service.auth.VerificationTokenGeneratingService;
import ibs124.gundi.service.auth.VerificationTokenCreatingService;
import ibs124.gundi.util.TestUtils;
import jakarta.validation.Valid;
import jakarta.validation.Validator;

@Service
class VerificationTokenCreatingServiceImpl implements VerificationTokenCreatingService {

    private final VerificationTokenGeneratingService tokenCreatingService;
    private final VerificationTokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final Validator validator;

    public VerificationTokenCreatingServiceImpl(
            VerificationTokenGeneratingService tokenConfiguringService,
            VerificationTokenRepository tokenRepository,
            UserRepository userRepository,
            Validator validator) {
        this.tokenCreatingService = tokenConfiguringService;
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.validator = validator;
    }

    @Override
    public TokenDto createById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TokenDto createByUsername(String username) {
        VerificationTokenEntity cache = this.tokenRepository
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

        VerificationTokenEntity newToken = new VerificationTokenEntity(user);

        return this.refreshToken(newToken);
    }

    private TokenDto refreshToken(VerificationTokenEntity token) {
        TokenDto tokenDto = this.tokenCreatingService.generateVerificationToken();

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
