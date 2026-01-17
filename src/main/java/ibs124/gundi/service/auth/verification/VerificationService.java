package ibs124.gundi.service.auth.verification;

public interface VerificationService {

    boolean verifyNewUserVerificationToken(String token);
}
