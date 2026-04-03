package ibs124.gundi.service.auth.token;

public interface PasswordResetValidationService {

    boolean isPasswordResetTokenValid(String token);

}