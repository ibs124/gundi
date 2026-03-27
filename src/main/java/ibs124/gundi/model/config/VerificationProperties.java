package ibs124.gundi.model.config;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@NotNull
public record VerificationProperties(
        int timeframeHours,
        @Valid VerificationEmailProperties mail,
        @Valid VerificationTokenProperties token) {

}
