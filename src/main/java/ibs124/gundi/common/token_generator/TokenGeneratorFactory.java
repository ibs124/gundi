package ibs124.gundi.common.token_generator;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import ibs124.gundi.common.token_generator.model.TokenGenerateRequest;

public class TokenGeneratorFactory {

    private SecureRandom secureRandom;
    private Map<String, TokenGenerateRequest> requests;

    public TokenGeneratorFactory() {
        super();
        this.requests = new HashMap<>();
    }

    public static TokenGeneratorFactory getInstance() {
        return new TokenGeneratorFactory();
    }

    public TokenGenerator build() {
        this.veirfyBuild();
        return new TokenGeneratorImpl(this.secureRandom, this.requests);
    }

    public TokenGeneratorFactory addRequest(String key, TokenGenerateRequest value) {
        this.requests.put(key, value);
        return this;
    }

    public TokenGeneratorFactory addSecureRandom(SecureRandom secureRandom) {
        if (secureRandom == null) {
            secureRandom = new SecureRandom();
        }

        this.secureRandom = secureRandom;

        return this;
    }

    private void veirfyBuild() {
        if (secureRandom == null) {
            secureRandom = new SecureRandom();
        }

        if (requests == null || requests.isEmpty()) {
            requests = new HashMap<>();
        }
    }

}
