package ibs124.gundi.service.auth.impl;

import ibs124.gundi.repository.PasswordResetTokenRepository;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ibs124.gundi.config.PropertyConfig;
import ibs124.gundi.model.dto.auth.TokenDto;
import ibs124.gundi.model.dto.config.VerificationProperties;
import ibs124.gundi.model.dto.config.VerificationTokenProperties;
import ibs124.gundi.repository.AbstractTokenRepository;
import ibs124.gundi.repository.VerificationTokenRepository;
import ibs124.gundi.service.auth.PasswordResetTokenCreatingService;
import ibs124.gundi.service.auth.VerificationTokenCreatingService;

@Service
public class AbstractTokenCreatingServiceImpl implements
        VerificationTokenCreatingService,
        PasswordResetTokenCreatingService {

    private final PropertyConfig config;
    private final SecureRandom secureRandom;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    public AbstractTokenCreatingServiceImpl(
            PropertyConfig config,
            SecureRandom secureRandom,
            VerificationTokenRepository verificationTokenRepository,
            PasswordResetTokenRepository passwordResetTokenRepository) {
        this.config = config;
        this.secureRandom = secureRandom;
        this.verificationTokenRepository = verificationTokenRepository;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
    }

    @Override
    public TokenDto createPasswordResetToken() {
        return this.prepareToken(
                this.config.verification(), this.passwordResetTokenRepository);
    }

    @Override
    public TokenDto createVerificationToken() {
        return this.prepareToken(
                this.config.verification(), this.verificationTokenRepository);
    }

    private TokenDto prepareToken(
            VerificationProperties config,
            AbstractTokenRepository<?> repo) {

        VerificationTokenProperties tokenConfig = config.token();
        boolean isLink = config.token().useLink();

        String secret = isLink
                ? this.createLinkSecret()
                : this.createOtpSecret(tokenConfig);

        while (repo.existsBySecret(secret)) {
            secret = isLink
                    ? this.createLinkSecret()
                    : this.createOtpSecret(tokenConfig);
        }

        return new TokenDto(secret, this.createExpiration(tokenConfig));
    }

    private String createOtpSecret(VerificationTokenProperties config) {
        int length = config.length();
        String[] charactrers = config.allowedCharacters();

        return this.secureRandom
                .ints(length, 0, charactrers.length)
                .mapToObj(x -> charactrers[x])
                .collect(Collectors.joining());
    }

    private String createLinkSecret() {
        return UUID.randomUUID().toString();
    }

    private Instant createExpiration(VerificationTokenProperties config) {
        return Instant
                .now()
                .plus(config.expirationMinutes(), ChronoUnit.MINUTES);
    }

}
