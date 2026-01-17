package ibs124.gundi.model.application;

import ibs124.gundi.validation.constraint.UniqueEmail;
import ibs124.gundi.validation.constraint.ValidEmail;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@NotNull
public record EmailCreateDto(
        @NotNull @Positive Long userId,
        @UniqueEmail @ValidEmail String emailAddress) {
}
