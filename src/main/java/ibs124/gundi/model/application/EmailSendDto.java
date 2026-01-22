package ibs124.gundi.model.application;

import org.springframework.lang.NonNull;

import jakarta.validation.constraints.NotNull;

@NotNull
public record EmailSendDto(
        @NonNull String from,
        @NonNull String displayName,
        @NonNull String[] to,
        @NonNull String subject,
        @NonNull String text,
        boolean isHtml) {
}
