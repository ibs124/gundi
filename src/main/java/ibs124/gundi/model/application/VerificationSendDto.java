package ibs124.gundi.model.application;

import ibs124.gundi.validation.constraint.ValidEmail;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@NotNull
public record VerificationSendDto(
        @ValidEmail String email,
        String appUrl,
        @PositiveOrZero Long userId) {

}
