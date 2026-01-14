package ibs124.gundi.service.auth;

public interface VerificationService {

    boolean verifyNewUserByToken(String token);
}
