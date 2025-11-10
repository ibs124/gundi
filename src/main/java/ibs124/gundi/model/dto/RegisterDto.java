package ibs124.gundi.model.dto;

public record RegisterDto(
        String email,
        String password,
        String fullName,
        String username) {
}