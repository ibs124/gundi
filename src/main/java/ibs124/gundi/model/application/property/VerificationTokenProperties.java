package ibs124.gundi.model.application.property;

public record VerificationTokenProperties(
        int expirationMinutes,
        boolean useLink,
        int length,
        String[] allowedCharacters) {
}
