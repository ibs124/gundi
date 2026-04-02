package ibs124.gundi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import ibs124.gundi.common.token_generator.config.TokenGeneratorConiguration;
import ibs124.gundi.common.token_generator.model.TokenGenerateRequest;
import ibs124.gundi.constant.Env;
import ibs124.gundi.model.config.VerificationProperties;

@ConfigurationProperties(prefix = Env.APP)
public record PropertyConfig(

        Debug debug,

        VerificationProperties verification,

        VerificationProperties passwordReset

) implements TokenGeneratorConiguration {

    public record Debug(
            boolean global,
            boolean verification,
            boolean passwordReset) {

        public Debug {
            if (global) {
                verification = true;
                passwordReset = true;
            }
        }
    }

    @Override
    public TokenGenerateRequest getVerificationTokenConfiguration() {
        return this.verification().token();
    }

    @Override
    public TokenGenerateRequest getRecoveryTokenConfiguration() {
        return this.passwordReset().token();
    }

}
