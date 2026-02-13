package ibs124.gundi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import ibs124.gundi.constant.Env;
import ibs124.gundi.model.properties.VerificationProperties;

@ConfigurationProperties(prefix = Env.APP)
public record PropertyConfiguration(
        VerificationProperties newUser) {
}
