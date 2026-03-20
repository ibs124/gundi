package ibs124.gundi.model.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@NotNull
public record UserDto(
        @NotNull @Positive Long userId,
        @NotBlank String verificationSecret) {
}
