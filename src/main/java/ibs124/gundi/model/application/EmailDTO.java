package ibs124.gundi.model.application;

import ibs124.gundi.validation.constraint.ValidEmail;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@NotNull
public record EmailDTO(
        @Positive long id,
        @Valid UserDTO user,
        @ValidEmail String name) {

}