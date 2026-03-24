package ibs124.gundi.service.auth.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import ibs124.gundi.exception.ResourceReadingException;
import ibs124.gundi.model.dto.auth.TokenDto;
import ibs124.gundi.model.entity.UserEntity;
import ibs124.gundi.model.entity.VerificationTokenEntity;
import ibs124.gundi.repository.UserRepository;
import ibs124.gundi.repository.VerificationTokenRepository;
import ibs124.gundi.service.auth.VerificationTokenConfigService;
import ibs124.gundi.service.auth.VerificationTokenCreatingService;
import ibs124.gundi.util.TestUtils;
import jakarta.validation.Valid;
import jakarta.validation.Validator;

@Service
class VerificationTokenCreatingServiceImpl implements VerificationTokenCreatingService {

    private final VerificationTokenConfigService tokenConfiguringService;
    private final VerificationTokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final Validator validator;

    public VerificationTokenCreatingServiceImpl(
            VerificationTokenConfigService tokenConfiguringService,
            VerificationTokenRepository tokenRepository,
            UserRepository userRepository,
            Validator validator) {
        this.tokenConfiguringService = tokenConfiguringService;
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.validator = validator;
    }

    @Override
    public TokenDto createByUsername(String username) {
        Optional<@Valid VerificationTokenEntity> cache = this.tokenRepository
                .findByUserUsernameOrUserPrimaryEmail(username, username);

        TokenDto tokenDto = this.checkChache(cache);

        if (tokenDto != null) {
            return tokenDto;
        }

        UserEntity user = this.userRepository
                .findByUsernameOrPrimaryEmail(username, username)
                .orElseThrow(() -> new ResourceReadingException());

        return this.createNew(user);
    }

    @Override
    public TokenDto createByUserId(Long id) {
        Optional<@Valid VerificationTokenEntity> cache = this.tokenRepository
                .findByUserId(id);

        TokenDto tokenDto = this.checkChache(cache);

        if (tokenDto != null) {
            return tokenDto;
        }

        return this.createNew(this.userRepository
                .getReferenceById(id));
    }

    private TokenDto checkChache(Optional<VerificationTokenEntity> cache) {
        return cache
                .filter(x -> x != null && this.validator.validate(x).isEmpty())
                .map(x -> new TokenDto(x.getSecret(), x.getExpiresAt()))
                .orElse(null);
    }

    private TokenDto createNew(UserEntity user) {
        TokenDto tokenDto = this.tokenConfiguringService
                .configureNewUserVerificationToken();

        VerificationTokenEntity token = TestUtils.createBy(user, tokenDto);

        token = this.tokenRepository.save(token);

        return new TokenDto(token.getSecret(), token.getExpiresAt());
    }

}
