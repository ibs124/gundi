package ibs124.gundi.model.application;

import java.time.Instant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@NotNull
public record TokenDto(@NotBlank String secret, Instant expiresAt) {

}
