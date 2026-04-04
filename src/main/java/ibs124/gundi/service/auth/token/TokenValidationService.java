package ibs124.gundi.service.auth.token;

public interface TokenValidationService {

    boolean isPasswordResetTokenValid(String token);

}