package ibs124.gundi.model.application;

import ibs124.gundi.validation.constraint.UniqueEmail;
import ibs124.gundi.validation.constraint.UniqueUsername;
import ibs124.gundi.validation.constraint.ValidEmail;
import ibs124.gundi.validation.constraint.ValidPassword;
import ibs124.gundi.validation.constraint.ValidUsername;

public record RegisterDTO(
        @UniqueUsername @ValidUsername String username,
        @ValidPassword String password,
        @UniqueEmail @ValidEmail String emailAddress,
        String fullName) implements UserCreateDTO {
}