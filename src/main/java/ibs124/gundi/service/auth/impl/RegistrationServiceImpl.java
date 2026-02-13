package ibs124.gundi.service.auth.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ibs124.gundi.config.PropertyConfiguration;
import ibs124.gundi.event.NewUserVerificationEvent;
import ibs124.gundi.exception.ResourceCreatingException;
import ibs124.gundi.mapper.UserMapper;
import ibs124.gundi.model.application.RegisterDto;
import ibs124.gundi.model.application.TokenDto;
import ibs124.gundi.model.application.UserCreateDto;
import ibs124.gundi.model.application.VerificationSendDto;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.model.domain.VerificationToken;
import ibs124.gundi.repository.VerificationTokenRepository;
import ibs124.gundi.service.auth.NewUserVerificationTokenCreatingService;
import ibs124.gundi.service.auth.RegistrationService;
import ibs124.gundi.service.auth.UserStateManagingService;
import jakarta.transaction.Transactional;

@Service
class RegistrationServiceImpl implements RegistrationService {

    private final UserMapper userMapper;
    private final VerificationTokenRepository tokenRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final NewUserVerificationTokenCreatingService tokenCreatingService;
    private final UserStateManagingService stateManagingService;

    public RegistrationServiceImpl(
            UserMapper userMapper,
            VerificationTokenRepository tokenRepository,
            ApplicationEventPublisher eventPublisher,
            NewUserVerificationTokenCreatingService tokenCreatingService,
            UserStateManagingService stateManagingService) {
        this.userMapper = userMapper;
        this.tokenRepository = tokenRepository;
        this.eventPublisher = eventPublisher;
        this.tokenCreatingService = tokenCreatingService;
        this.stateManagingService = stateManagingService;
    }

    @Override
    @Transactional
    public Long registerUser(RegisterDto request) {
        try {
            String email = request.user().primaryEmail();
            String appUrl = request.appUrl();

            User user = this.createUser(request.user());

            String token = this.createTokenByUser(user);

            this.sendVerification(token, appUrl, email);

            return user.getId();

        } catch (Exception e) {
            String message = "Unexpected error occured while creating "
                    + User.class.toString();

            ResourceCreatingException error = new ResourceCreatingException(message, e);
            error.setTarget(User.class);

            throw error;

        }
    }

    public void sendVerification(String token, String appUrl, String email) {
        var request = new VerificationSendDto(email, token, appUrl);

        var event = new NewUserVerificationEvent(request);

        this.eventPublisher.publishEvent(event);
    }

    public String createTokenByUser(User user) {
        TokenDto tokenMeta = this.tokenCreatingService.createNewUserVerificationToken();

        while (this.tokenRepository.existsBySecret(tokenMeta.secret())) {
            tokenMeta = this.tokenCreatingService.createNewUserVerificationToken();
        }

        VerificationToken token = new VerificationToken();

        token.setUser(user);
        token.setSecret(tokenMeta.secret());
        token.setExpiresAt(tokenMeta.expiresAt());

        token = this.tokenRepository.save(token);

        return token.getSecret();
    }

    public User createUser(UserCreateDto request) {
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