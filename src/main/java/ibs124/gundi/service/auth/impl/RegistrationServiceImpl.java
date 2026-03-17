package ibs124.gundi.service.auth.impl;

import java.time.Instant;

import org.springframework.stereotype.Service;

import ibs124.gundi.mapper.UserMapper;
import ibs124.gundi.model.application.RegisterDto;
import ibs124.gundi.model.application.RegisterResponseDto;
import ibs124.gundi.model.application.TokenDto;
import ibs124.gundi.model.persistence.User;
import ibs124.gundi.model.persistence.VerificationToken;
import ibs124.gundi.repository.VerificationTokenRepository;
import ibs124.gundi.service.auth.VerificationTokenConfigService;
import ibs124.gundi.util.TestUtils;
import ibs124.gundi.service.auth.RegistrationService;
import ibs124.gundi.service.auth.UserConfigService;
import jakarta.transaction.Transactional;

@Service
class RegistrationServiceImpl implements RegistrationService {

    private final UserMapper userMapper;
    private final VerificationTokenRepository tokenRepository;
    private final VerificationTokenConfigService tokenCreatingService;
    private final UserConfigService stateManagingService;

    public RegistrationServiceImpl(
            UserMapper userMapper,
            VerificationTokenRepository tokenRepository,
            VerificationTokenConfigService tokenCreatingService,
            UserConfigService stateManagingService) {
        this.userMapper = userMapper;
        this.tokenRepository = tokenRepository;
        this.tokenCreatingService = tokenCreatingService;
        this.stateManagingService = stateManagingService;
    }

    @Override
    @Transactional
    public RegisterResponseDto register(RegisterDto request) {
        User user = this.createUser(request);
        VerificationToken token = this.createTokenByUser(user);
        return new RegisterResponseDto(token.getUser().getId(), token.getSecret());
    }

    public VerificationToken createTokenByUser(User user) {
        TokenDto tokenDto = this.tokenCreatingService.configureNewUserVerificationToken();
        VerificationToken token = TestUtils.createBy(user, tokenDto);
        return this.tokenRepository.save(token);
    }

    public User createUser(RegisterDto request) {
        User user = this.userMapper
                .mapToDomainModel(request);

        user.setPassword(
                this.stateManagingService
                        .encodePassword(request.password()));

        user.setAccountExpiresAt(
                this.stateManagingService.computeNewUserAccountExpiration());

        user.setMfaEnabledAt(Instant.now());

        return user;
    }
}