package ibs124.gundi.common.token_generator.config;

import java.util.Map;

import ibs124.gundi.common.token_generator.model.TokenGenerateRequest;

public interface TokenGeneratorCustomizer {

    Map<String, TokenGenerateRequest> getCustomizations();

    boolean addCustomization(String key, TokenGenerateRequest request);

    boolean removeCustomization(String key, TokenGenerateRequest request);
}
