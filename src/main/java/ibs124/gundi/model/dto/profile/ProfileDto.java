package ibs124.gundi.model.dto.profile;

import jakarta.validation.constraints.NotNull;

@NotNull
public record ProfileDto(
        Long id,
        String username,
        String email,
        String fullName) {

}
