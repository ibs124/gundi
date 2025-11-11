package ibs124.gundi.model.api;

import ibs124.gundi.validation.constraint.ValidEmail;
import ibs124.gundi.validation.constraint.ValidFullName;
import ibs124.gundi.validation.constraint.ValidPassword;
import ibs124.gundi.validation.constraint.ValidUsername;

public record RegisterApiRequest(
        @ValidEmail String email,
        @ValidPassword String password,
        @ValidFullName String fullName,
        @ValidUsername String username) {
}