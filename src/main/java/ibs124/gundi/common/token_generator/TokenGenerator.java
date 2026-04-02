package ibs124.gundi.common.token_generator;

import ibs124.gundi.common.token_generator.model.TokenGenerateRequest;
import ibs124.gundi.common.token_generator.model.TokenGenerateResponse;

public interface TokenGenerator {

    TokenGenerateResponse generate(TokenGenerateRequest request);

    TokenGenerateResponse generateBySecret(String request);

}
