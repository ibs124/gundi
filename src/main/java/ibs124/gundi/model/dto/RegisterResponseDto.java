package ibs124.gundi.model.dto;

import jakarta.validation.Valid;

@Valid
public record RegisterResponseDto(@Valid UserDto user) {

}
