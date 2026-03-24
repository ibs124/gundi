package ibs124.gundi.model.dto.config;

import ibs124.gundi.validation.constraint.ValidEmail;
import jakarta.validation.constraints.NotNull;

@NotNull
public record VerificationEmailProperties(
        @ValidEmail String from,
        String displayName,
        String subject,
        boolean isHtml) {
}