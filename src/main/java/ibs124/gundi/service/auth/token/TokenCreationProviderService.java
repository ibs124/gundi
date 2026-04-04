package ibs124.gundi.service.auth.token;

import ibs124.gundi.model.dto.auth.TokenContract;
import ibs124.gundi.model.dto.auth.TokenCreateRequest;

public interface TokenCreationProviderService<T extends TokenCreateRequest> {

    TokenContract create(T request);

}