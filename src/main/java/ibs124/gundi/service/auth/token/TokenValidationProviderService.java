package ibs124.gundi.service.auth.token;

import ibs124.gundi.model.dto.auth.TokenConsumeRequest;

public interface TokenValidationProviderService<T extends TokenConsumeRequest> {

    boolean isTokenValid(T token);

}