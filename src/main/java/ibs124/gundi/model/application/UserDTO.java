package ibs124.gundi.model.application;

import java.util.Set;

import ibs124.gundi.validation.constraint.ValidFullName;
import ibs124.gundi.validation.constraint.ValidUsername;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@NotNull
public record UserDTO(
        @PositiveOrZero long id,
        boolean enabled,
        Set<@Valid RoleDTO> roles,
        @ValidUsername String username,
        @ValidFullName String fullName) {

    public UserDTO {
        roles = Set.copyOf(roles);
    }
}