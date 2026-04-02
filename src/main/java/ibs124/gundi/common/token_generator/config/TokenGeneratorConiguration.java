package ibs124.gundi.common.token_generator.config;

import ibs124.gundi.common.token_generator.model.TokenGenerateRequest;

public interface TokenGeneratorConiguration {

    TokenGenerateRequest getVerificationTokenConfiguration();

    TokenGenerateRequest getRecoveryTokenConfiguration();

}
