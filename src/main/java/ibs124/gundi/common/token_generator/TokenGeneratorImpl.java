package ibs124.gundi.common.token_generator;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import ibs124.gundi.common.token_generator.model.TokenGenerateRequest;
import ibs124.gundi.common.token_generator.model.TokenGenerateResponse;

class TokenGeneratorImpl implements TokenGenerator {

    static final int DEFAULT_LENGTH = 6;

    static final Duration DEFAULT_EXPIRATION = Duration.ofMinutes(DEFAULT_LENGTH);

    static final String[] DEFAULT_CHARS = {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

    private final SecureRandom secureRandom;
    private final Map<String, TokenGenerateRequest> requests;

    public TokenGeneratorImpl(
            SecureRandom secureRandom,
            Map<String, TokenGenerateRequest> requests) {

        if (requests == null) {
            requests = new HashMap<>();
        }

        this.secureRandom = secureRandom;
        this.requests = requests;
    }

    @Override
    public TokenGenerateResponse generateBySecret(String secret) {

        if (secret == null) {
            return this.generateDefault();
        }

        TokenGenerateRequest request = this.requests.get(secret);

        return this.generate(request);
    }

    @Override
    public TokenGenerateResponse generate(TokenGenerateRequest request) {

        if (request == null) {
            return this.generateDefault();
        }

        String secret = request.isDeliveredAsLink()
                ? this.generateLinkSecret()
                : this.generateOtpSecret(
                        request.getLength(), request.getAllowedCharacters());

        Instant expiresAt = this.generateExpiration(request.getExpiration());

        return TokenGenerateResponse.of(secret, expiresAt);
    }

    private TokenGenerateResponse generateDefault() {
        return TokenGenerateResponse.of(
                this.generateOtpSecret(DEFAULT_LENGTH, DEFAULT_CHARS),
                this.generateExpiration(DEFAULT_EXPIRATION));
    }

    private String generateOtpSecret(int length, String[] chars) {
        if (length < DEFAULT_LENGTH) {
            length = DEFAULT_LENGTH;
        }

        String[] safeChars;

        if (chars == null || chars.length < DEFAULT_CHARS.length) {
            safeChars = DEFAULT_CHARS;
        } else {
            safeChars = chars;
        }

        return this.secureRandom
                .ints(length, 0, safeChars.length)
                .mapToObj(x -> safeChars[x])
                .collect(Collectors.joining());
    }

    private String generateLinkSecret() {
        return UUID.randomUUID().toString();
    }

    private Instant generateExpiration(Duration duration) {
        if (duration == null || duration.toMinutes() < DEFAULT_EXPIRATION.toMinutes()) {
            duration = DEFAULT_EXPIRATION;
        }

        return Instant.now().plus(duration);
    }

}