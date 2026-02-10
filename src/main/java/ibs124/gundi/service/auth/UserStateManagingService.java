package ibs124.gundi.service.auth;

import java.time.Instant;

public interface UserStateManagingService {

    Instant computeNewUserAccountExpiration();

    String encodePassword(String raw);

}