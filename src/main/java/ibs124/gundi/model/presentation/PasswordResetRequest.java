package ibs124.gundi.model.presentation;

import ibs124.gundi.validation.constraint.ValidPassword;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@NotNull
public record PasswordResetRequest(
        @NotBlank String token,
        @ValidPassword String password,
        String passwordConfirm) {

    public PasswordResetRequest(String token) {
        this(token, null, null);
    }

}
