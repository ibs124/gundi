package ibs124.gundi.service.auth;

public interface VerificationTokenCreatingService {

    String createVerificationTokenByUserId(Long id);

}