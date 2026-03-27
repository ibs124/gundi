package ibs124.gundi.model.config;

public record VerificationTokenProperties(
        int expirationMinutes,
        boolean useLink,
        int length,
        String[] allowedCharacters) {
}
