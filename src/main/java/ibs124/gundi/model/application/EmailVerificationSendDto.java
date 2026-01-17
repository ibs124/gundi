package ibs124.gundi.model.application;

import org.springframework.lang.NonNull;

public record EmailVerificationSendDto(
        @NonNull String from,
        @NonNull String displayName,
        @NonNull String to,
        @NonNull String subject,
        @NonNull String text,
        boolean isHtml) {
}
