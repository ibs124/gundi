package ibs124.gundi.security;

import java.time.Instant;

import org.springframework.security.authentication.ott.OneTimeToken;

public record MyOtt(
        String username,
        String secret,
        Instant expiresAt) implements OneTimeToken {

    @Override
    public String getTokenValue() {
        return this.secret();
    }

    @Override
    public String getUsername() {
        return this.username();
    }

    @Override
    public Instant getExpiresAt() {
        return this.expiresAt();
    }
}