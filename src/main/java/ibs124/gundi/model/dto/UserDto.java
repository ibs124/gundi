package ibs124.gundi.model.dto;

import java.time.Instant;
import java.util.Set;

import ibs124.gundi.validation.constraint.ValidEmail;
import ibs124.gundi.validation.constraint.ValidUsername;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;

public record UserDto(
        @PositiveOrZero long id,
        Instant createdAt,
        Instant updatedAt,
        Set<@Valid UserRoleDto> roles,
        @ValidEmail String email,
        @ValidUsername String username) {

    public UserDto {
        roles = Set.copyOf(roles);
    }
}