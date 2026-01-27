package ibs124.gundi.model.application;

import ibs124.gundi.model.enumm.VerificationType;
import ibs124.gundi.validation.constraint.ValidEmail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@NotNull
public record VerificationSendDto(
        @NotNull VerificationType type,
        @ValidEmail String email,
        @NotBlank String token,
        String appUrl) {

}
