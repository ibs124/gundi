package ibs124.gundi.model.application;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@NotNull
public record RegisterDTO(@Valid UserCreateDTO user, String appURL) {

}
