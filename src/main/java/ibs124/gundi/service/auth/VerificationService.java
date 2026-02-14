package ibs124.gundi.service.auth;

public interface VerificationService {

    boolean verifyBySecret(String secret);
}
