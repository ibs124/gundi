package ibs124.gundi.service.auth.impl;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ibs124.gundi.config.PropertyConfig;
import ibs124.gundi.model.application.TokenDto;
import ibs124.gundi.model.properties.VerificationProperties;
import ibs124.gundi.model.properties.VerificationTokenProperties;
import ibs124.gundi.repository.VerificationTokenRepository;
import ibs124.gundi.service.auth.VerificationTokenCreatingService;

@Service
public class TokenCreatingServiceImpl implements
        VerificationTokenCreatingService {

    private final PropertyConfig config;
    private final SecureRandom secureRandom;
    private final VerificationTokenRepository verificationTokenRepository;

    public TokenCreatingServiceImpl(
            PropertyConfig config,
            SecureRandom secureRandom,
            VerificationTokenRepository verificationTokenRepository) {
        this.config = config;
        this.secureRandom = secureRandom;
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Override
    public TokenDto createNewUserVerificationToken() {
        TokenDto token = this.prepareToken(this.config.newUser());

        while (this.verificationTokenRepository.existsBySecret(token.secret())) {
            token = this.prepareToken(this.config.newUser());
        }

        return token;
    }

    private TokenDto prepareToken(VerificationProperties config) {
        String secret = config.token().useLink()
                ? this.createLinkSecret()
                : this.createOtpSecret(config.token());

        return new TokenDto(secret, this.createExpiration(config.token()));
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
