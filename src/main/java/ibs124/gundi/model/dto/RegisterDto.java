package ibs124.gundi.model.dto;

import ibs124.gundi.validation.constraint.UniqueEmail;
import ibs124.gundi.validation.constraint.UniqueUsername;
import ibs124.gundi.validation.constraint.ValidEmail;
import ibs124.gundi.validation.constraint.ValidPassword;
import ibs124.gundi.validation.constraint.ValidUsername;

public record RegisterDto(
        @UniqueUsername @ValidUsername String username,
        @ValidPassword String password,
        @UniqueEmail @ValidEmail String email,
        String fullName) {
}