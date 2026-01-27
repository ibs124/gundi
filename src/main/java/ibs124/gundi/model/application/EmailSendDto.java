package ibs124.gundi.model.application;

import org.springframework.lang.NonNull;

import ibs124.gundi.model.properties.VerificationEmailProperties;
import jakarta.validation.constraints.NotNull;

@NotNull
public record EmailSendDto(
        @NonNull String from,
        @NonNull String displayName,
        @NonNull String[] to,
        @NonNull String subject,
        @NonNull String text,
        boolean isHtml) {

    public EmailSendDto(
            VerificationEmailProperties props, String text, String... to) {
        this(
                props.from(),
                props.displayName(),
                to,
                props.subject(),
                text,
                props.isHtml());
    }
}
