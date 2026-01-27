package ibs124.gundi.model.properties;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@NotNull
public record VerificationProperties(
        @Valid VerificationEmailProperties mail,
        boolean userVerificationLink,
        int tokenExpirationMinutes,
        int verificationDeadlineHours) {

}
