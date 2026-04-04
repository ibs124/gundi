package ibs124.gundi.service.auth.token;

import ibs124.gundi.model.dto.auth.TokenConsumeRequest;
import ibs124.gundi.model.dto.auth.TokenContract;

public interface TokenConsumptionPoviderService<T extends TokenConsumeRequest> {

    TokenContract consume(T request);
}
