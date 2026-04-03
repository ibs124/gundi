package ibs124.gundi.model.dto.auth;

import java.time.Instant;

import ibs124.gundi.common.token_generator.model.TokenGenerateResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@NotNull
public record TokenDto(
        String username,
        @NotBlank String secret,
        @NotNull Instant expiresAt) implements TokenGenerateResponse, TokenContract {

    public TokenDto(String secret, Instant expiresAt) {
        this(null, secret, expiresAt);
    }

    @Override
    public String getUsername() {
        return this.username();
    }

    @Override
    public String getSecret() {
        return this.secret();
    }

    @Override
    public Instant getExpiresAt() {
        return this.expiresAt();
    }
}
