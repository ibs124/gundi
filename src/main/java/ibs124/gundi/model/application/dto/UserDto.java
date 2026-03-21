package ibs124.gundi.model.application.dto;

import java.util.Collection;
import java.util.HashSet;

import ibs124.gundi.model.application.Authority;
import ibs124.gundi.validation.constraint.ValidEmail;
import ibs124.gundi.validation.constraint.ValidFullName;
import ibs124.gundi.validation.constraint.ValidUsername;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@NotNull
public record UserDto(
        @NotNull @PositiveOrZero Long id,
        @NotNull Collection<Authority> authorities,
        @ValidUsername String username,
        @ValidEmail String primaryEmail,
        @ValidFullName String fullName,
        boolean isEnabled) {

    public UserDto {
        if (authorities == null) {
            authorities = new HashSet<>();
        }
    }

    public UserDto addAuthority(Authority arg) {
        if (arg == null) {
            return this;
        }

        this.authorities.add(arg);

        return this;
    }

    public UserDto removeAuthority(Authority arg) {
        if (arg == null) {
            return this;
        }

        this.authorities.remove(arg);

        return this;
    }
}
