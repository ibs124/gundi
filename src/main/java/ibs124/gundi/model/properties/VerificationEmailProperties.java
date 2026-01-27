package ibs124.gundi.model.properties;

import ibs124.gundi.validation.constraint.ValidEmail;
import jakarta.validation.constraints.NotNull;

@NotNull
public record VerificationEmailProperties(
        @ValidEmail String from,
        String displayName,
        String subject,
        boolean isHtml) {
}