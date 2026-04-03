package ibs124.gundi.service.auth;

import ibs124.gundi.model.dto.auth.TokenConsumeRequest;
import ibs124.gundi.model.dto.auth.TokenContract;

public interface AbstractTokenConsumingService<T extends TokenConsumeRequest> {

    TokenContract consume(T request);
}
