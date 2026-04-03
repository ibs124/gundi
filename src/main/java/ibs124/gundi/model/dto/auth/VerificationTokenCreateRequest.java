package ibs124.gundi.model.dto.auth;

public record VerificationTokenCreateRequest(
        String username,
        long userId) implements TokenCreateRequest {

    public static VerificationTokenCreateRequest of(String username) {
        return new VerificationTokenCreateRequest(username, 0);
    }

    @Override
    public String getUsername() {
        return this.username();
    }
}