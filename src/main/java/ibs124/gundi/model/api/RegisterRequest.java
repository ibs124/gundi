package ibs124.gundi.model.api;

import ibs124.gundi.validation.constraint.ValidEmail;
import ibs124.gundi.validation.constraint.ValidPassword;
import ibs124.gundi.validation.constraint.ValidUsername;

public record RegisterRequest(
        @ValidEmail String email,
        @ValidPassword String password,
        String fullName,
        @ValidUsername String username) {
}