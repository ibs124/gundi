package ibs124.gundi.model.properties;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@NotNull
public record VerificationProperties(
        @Valid MailProperties mail,
        int tokenExpirationMinutes,
        int verificationDeadlineHours) {

}
