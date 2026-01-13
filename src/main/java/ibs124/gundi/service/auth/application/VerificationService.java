package ibs124.gundi.service.auth.application;

public interface VerificationService {

    boolean verifyNewUserByToken(String token);
}
