package ibs124.gundi.service.auth;

public interface VerificationService {

    boolean verifyNewUserVerificationToken(String token);
}
