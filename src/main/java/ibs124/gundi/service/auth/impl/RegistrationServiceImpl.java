package ibs124.gundi.service.auth.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ibs124.gundi.configuration.PropertyConfig;
import ibs124.gundi.event.UserVerificationEvent;
import ibs124.gundi.exception.ResourceCreatingException;
import ibs124.gundi.mapper.UserMapper;
import ibs124.gundi.model.application.RegisterDto;
import ibs124.gundi.model.application.UserCreateDto;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.model.domain.VerificationToken;
import ibs124.gundi.model.enumm.VerificationType;
import ibs124.gundi.repository.VerificationTokenRepository;
import ibs124.gundi.service.auth.RegistrationService;
import jakarta.transaction.Transactional;

@Service
class RegistrationServiceImpl implements RegistrationService {

    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final VerificationTokenRepository tokenRepository;
    private final PropertyConfig config;
    private final ApplicationEventPublisher eventPublisher;

    public RegistrationServiceImpl(
            PasswordEncoder passwordEncoder,
            UserMapper userMapper,
            VerificationTokenRepository tokenRepository,
            PropertyConfig config,
            ApplicationEventPublisher eventPublisher) {
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.tokenRepository = tokenRepository;
        this.config = config;
        this.eventPublisher = eventPublisher;
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
        UserVerificationEvent event = new UserVerificationEvent(token, email, appUrl);

        this.eventPublisher.publishEvent(event);
    }

    public String createTokenByUser(User user) {
        VerificationToken token = new VerificationToken();

        token.setOwner(user);
        token.setType(VerificationType.NEW_USER);
        token.setValue(this.createLinkToken());
        token.setExpiresAt(this.createExpiration());

        token = this.tokenRepository.save(token);

        return token.getValue();
    }

    private Instant createExpiration() {
        int minutes = this.config.newUser().tokenExpirationMinutes();
        return Instant
                .now()
                .plus(minutes, ChronoUnit.MINUTES);
    }

    private String createLinkToken() {
        String value = UUID.randomUUID().toString();

        while (this.tokenRepository.existsByValue(value)) {
            value = UUID.randomUUID().toString();
        }

        return value;
    }

    public User createUser(UserCreateDto request) {
        String encodedPassword = this.passwordEncoder
                .encode(request.password());

        User user = this.userMapper
                .mapToDomainModel(request);

        user.setPassword(encodedPassword);

        Instant accountExpiresAt = Instant
                .now()
                .plus(
                        this.config.newUser().verificationDeadlineHours(),
                        ChronoUnit.HOURS);

        user.setAccountExpiresAt(accountExpiresAt);

        // user = this.userRepository.save(user);
        return user;

    }
}