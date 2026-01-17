package ibs124.gundi.service.auth;

public interface VerificationTokenCreatingService {

    String createNewUserVerificationTokenByUserId(Long userId);
}