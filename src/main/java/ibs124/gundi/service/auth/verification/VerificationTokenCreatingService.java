package ibs124.gundi.service.auth.verification;

public interface VerificationTokenCreatingService {

    String createNewUserVerificationTokenByUserId(Long userId);
}