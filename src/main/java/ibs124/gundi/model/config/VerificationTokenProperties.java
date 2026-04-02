package ibs124.gundi.model.config;

import java.time.Duration;

import ibs124.gundi.common.token_generator.model.TokenGenerateRequest;

public record VerificationTokenProperties(
        int expirationMinutes,
        boolean useLink,
        int length,
        String[] allowedCharacters) implements TokenGenerateRequest {

    @Override
    public boolean isDeliveredAsLink() {
        return this.useLink;
    }

    @Override
    public int getLength() {
        return this.length();
    }

    @Override
    public String[] getAllowedCharacters() {
        return this.allowedCharacters();
    }

    @Override
    public Duration getExpiration() {
        return Duration.ofMinutes(this.expirationMinutes());
    }
}
