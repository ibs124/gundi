package ibs124.gundi.common.token_generator.model;

import java.time.Instant;

public interface TokenGenerateResponse {

    String getSecret();

    Instant getExpiresAt();

    public static TokenGenerateResponse of(String secret, Instant expiresAt) {
        return new TokenGenerateResponse() {

            @Override
            public String getSecret() {
                return secret;
            }

            @Override
            public Instant getExpiresAt() {
                return expiresAt;
            }
        };
    }
}
