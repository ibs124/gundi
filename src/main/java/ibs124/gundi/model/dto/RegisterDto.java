package ibs124.gundi.model.dto;

import ibs124.gundi.validation.constraint.ValidEmail;
import ibs124.gundi.validation.constraint.ValidPassword;
import ibs124.gundi.validation.constraint.ValidUsername;

public record RegisterDto(
        @ValidUsername String username,
        @ValidPassword String password,
        @ValidEmail String email,
        String fullName) {
}