package ibs124.gundi.model.application.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@NotNull
public record UserVerifiedResponseDto(@Valid UserDto user, @Valid TokenDto token) {

}
