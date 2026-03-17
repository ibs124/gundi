package ibs124.gundi.model.application.dto;

import java.time.Instant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@NotNull
public record TokenDto(
        String username,
        @NotBlank String secret,
        Instant expiresAt) {

    public TokenDto(String secret, Instant expiresAt) {
        this(null, secret, expiresAt);
    }

}
