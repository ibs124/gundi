package ibs124.gundi.model.application.dto;

import ibs124.gundi.validation.constraint.UniqueEmail;
import ibs124.gundi.validation.constraint.UniqueUsername;
import ibs124.gundi.validation.constraint.ValidEmail;
import ibs124.gundi.validation.constraint.ValidFullName;
import ibs124.gundi.validation.constraint.ValidPassword;
import ibs124.gundi.validation.constraint.ValidUsername;
import jakarta.validation.constraints.NotNull;

@NotNull
public record RegisterDto(
        @UniqueUsername @ValidUsername String username,
        @ValidPassword String password,
        @UniqueEmail @ValidEmail String email,
        @ValidFullName String fullName) {

    public RegisterDto() {
        this(null, null, null, null);
    }
}