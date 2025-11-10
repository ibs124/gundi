package ibs124.gundi.model.api;

public record RegisterApiRequest(
        String email,
        String password,
        String fullName,
        String username) {
}