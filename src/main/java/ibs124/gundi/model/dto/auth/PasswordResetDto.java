package ibs124.gundi.model.dto.auth;

import ibs124.gundi.validation.constraint.ValidPassword;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@NotNull
public record PasswordResetDto(
        @NotBlank String secret,
        @ValidPassword String password) {
}
