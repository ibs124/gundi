package ibs124.gundi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import ibs124.gundi.constant.Env;
import ibs124.gundi.model.config.VerificationProperties;

@ConfigurationProperties(prefix = Env.APP)
public record PropertyConfig(

        Debug debug,

        VerificationProperties verification,

        VerificationProperties passwordReset

) {

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

}
