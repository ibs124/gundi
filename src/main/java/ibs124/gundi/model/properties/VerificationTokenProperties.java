package ibs124.gundi.model.properties;

public record VerificationTokenProperties(
        int expirationMinutes,
        boolean useLink,
        int length,
        String[] allowedCharacters) {
}
