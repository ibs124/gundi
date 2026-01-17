package ibs124.gundi.model.presentation;

import jakarta.validation.constraints.NotNull;

@NotNull
public record UserMeView(
        Long id,
        String username,
        String primaryEmail,
        String fullName) {
}
