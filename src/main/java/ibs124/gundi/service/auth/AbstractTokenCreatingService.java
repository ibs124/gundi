package ibs124.gundi.service.auth;

import ibs124.gundi.model.dto.auth.TokenContract;
import ibs124.gundi.model.dto.auth.TokenCreateRequest;

public interface AbstractTokenCreatingService<T extends TokenCreateRequest> {

    TokenContract create(T request);

}