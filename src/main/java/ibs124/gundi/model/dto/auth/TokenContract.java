package ibs124.gundi.model.dto.auth;

import java.time.Instant;

public interface TokenContract {

    String getUsername();

    String getSecret();

    Instant getExpiresAt();

}
