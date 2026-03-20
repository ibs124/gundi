package ibs124.gundi.service.auth.impl;

import java.time.Instant;

import org.springframework.stereotype.Service;

import ibs124.gundi.mapper.UserMapper;
import ibs124.gundi.model.application.dto.UserCreateDto;
import ibs124.gundi.model.application.dto.UserDto;
import ibs124.gundi.model.application.dto.TokenDto;
import ibs124.gundi.model.persistence.UserEntity;
import ibs124.gundi.model.persistence.VerificationTokenEntity;
import ibs124.gundi.repository.VerificationTokenRepository;
import ibs124.gundi.service.auth.VerificationTokenConfigService;
import ibs124.gundi.util.TestUtils;
import ibs124.gundi.service.auth.UserCreatingService;
import ibs124.gundi.service.auth.UserConfiguringService;
import jakarta.transaction.Transactional;

@Service
class UserCreatingServiceImpl implements UserCreatingService {

    private final UserMapper userMapper;
    private final VerificationTokenRepository tokenRepository;
    private final VerificationTokenConfigService tokenCreatingService;
    private final UserConfiguringService stateManagingService;

    public UserCreatingServiceImpl(
            UserMapper userMapper,
            VerificationTokenRepository tokenRepository,
            VerificationTokenConfigService tokenCreatingService,
            UserConfiguringService stateManagingService) {
        this.userMapper = userMapper;
        this.tokenRepository = tokenRepository;
        this.tokenCreatingService = tokenCreatingService;
        this.stateManagingService = stateManagingService;
    }

    @Override
    @Transactional
    public UserDto create(UserCreateDto request) {
        UserEntity user = this.createUser(request);
        VerificationTokenEntity token = this.createTokenByUser(user);
        return new UserDto(token.getUser().getId(), token.getSecret());
    }

    public VerificationTokenEntity createTokenByUser(UserEntity user) {
        TokenDto tokenDto = this.tokenCreatingService.configureNewUserVerificationToken();
        VerificationTokenEntity token = TestUtils.createBy(user, tokenDto);
        return this.tokenRepository.save(token);
    }

    public UserEntity createUser(UserCreateDto request) {
        UserEntity user = this.userMapper
                .mapToPersistenceModel(request);

        user.setPassword(
                this.stateManagingService
                        .encodePassword(request.password()));

        user.setAccountExpiresAt(
                this.stateManagingService.getAccountExpiration());

        user.setMfaEnabledAt(Instant.now());

        return user;
    }
}