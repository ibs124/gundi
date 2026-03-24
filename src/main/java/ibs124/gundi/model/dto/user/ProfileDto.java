package ibs124.gundi.model.dto.user;

import jakarta.validation.constraints.NotNull;

@NotNull
public record ProfileDto(
        Long id,
        String username,
        String email,
        String fullName) {
}
