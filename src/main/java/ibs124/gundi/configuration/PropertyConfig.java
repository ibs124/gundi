package ibs124.gundi.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import ibs124.gundi.constant.CommonConstants;

@ConfigurationProperties(prefix = CommonConstants.APP)
public record PropertyConfig(
        String mailFrom,
        String mailDisplayName,
        boolean mailHtml,
        int tokenExpirationMinutes) {
}
