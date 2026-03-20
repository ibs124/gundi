package ibs124.gundi.service.auth;

import java.time.Instant;

public interface UserConfiguringService {

    Instant getAccountExpiration();

    String encodePassword(String raw);

}