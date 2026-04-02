package ibs124.gundi.common.token_generator.model;

import java.time.Duration;

public interface TokenGenerateRequest {

    boolean isDeliveredAsLink();

    int getLength();

    String[] getAllowedCharacters();

    Duration getExpiration();
}
