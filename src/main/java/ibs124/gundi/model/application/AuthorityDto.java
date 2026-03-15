package ibs124.gundi.model.application;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@NotNull
public record AuthorityDto(@PositiveOrZero long id, @NotBlank String name) {

}
