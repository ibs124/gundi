package ibs124.gundi.service.auth.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ibs124.gundi.configuration.PropertyConfiguration;
import ibs124.gundi.event.UserVerificationEvent;
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
import jakarta.transaction.Transactional;

@Service
class RegistrationServiceImpl implements RegistrationService {

    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final VerificationTokenRepository tokenRepository;
    private final PropertyConfiguration config;
    private final ApplicationEventPublisher eventPublisher;
    private final NewUserVerificationTokenCreatingService tokenCreatingService;

    public RegistrationServiceImpl(
            PasswordEncoder passwordEncoder,
            UserMapper userMapper,
            VerificationTokenRepository tokenRepository,
            PropertyConfiguration config,
            ApplicationEventPublisher eventPublisher,
            NewUserVerificationTokenCreatingService tokenCreatingService) {
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.tokenRepository = tokenRepository;
        this.config = config;
        this.eventPublisher = eventPublisher;
        this.tokenCreatingService = tokenCreatingService;
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

        var event = new UserVerificationEvent(request);

        this.eventPublisher.publishEvent(event);
    }

    public String createTokenByUser(User user) {
        TokenDto tokenMeta = this.tokenCreatingService.createNewUserVerificationToken();

        while (this.tokenRepository.existsByValue(tokenMeta.secret())) {
            tokenMeta = this.tokenCreatingService.createNewUserVerificationToken();
        }

        VerificationToken token = new VerificationToken();

        token.setUser(user);
        token.setValue(tokenMeta.secret());
        token.setExpiresAt(tokenMeta.expiresAt());

        token = this.tokenRepository.save(token);

        return token.getValue();
    }

    public User createUser(UserCreateDto request) {
        User user = this.userMapper
                .mapToDomainModel(request);

        String encodedPassword = this.passwordEncoder
                .encode(request.password());

        user.setPassword(encodedPassword);

        Instant now = Instant.now();

        Instant accountExpiresAt = now.plus(
                this.config.newUser().timeframeHours(), ChronoUnit.HOURS);

        user.setAccountExpiresAt(accountExpiresAt);

        user.setMfaEnabledAt(now);

        return user;

    }
}