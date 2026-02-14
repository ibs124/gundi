package ibs124.gundi.service.auth;

import java.time.Instant;

public interface UserSecurityService {

    Instant computeNewUserAccountExpiration();

    String encodePassword(String raw);

}