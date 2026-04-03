package ibs124.gundi.service.auth.user;

public interface PasswordResetValidationService {

    boolean isPasswordResetTokenValid(String token);

}