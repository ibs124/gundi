package ibs124.gundi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import ibs124.gundi.constant.Env;
import ibs124.gundi.model.configuration.VerificationProperties;

@ConfigurationProperties(prefix = Env.APP)
public record PropertyConfig(
        VerificationProperties newUser) {
}
