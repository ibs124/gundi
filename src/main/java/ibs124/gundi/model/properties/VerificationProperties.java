package ibs124.gundi.model.properties;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@NotNull
public record VerificationProperties(
        int timeframeHours,
        @Valid VerificationEmailProperties mail,
        @Valid VerificationTokenProperties token) {

}
