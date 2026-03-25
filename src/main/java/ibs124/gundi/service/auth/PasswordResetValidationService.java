package ibs124.gundi.service.auth;

public interface PasswordResetValidationService {

    boolean isPasswordResetTokenValid(String token);

}