package ibs124.gundi.configuration;

import java.security.SecureRandom;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import ibs124.gundi.constant.Env;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    LocalValidatorFactoryBean localValidatorFactoryBean() {
        var validatorFactory = new LocalValidatorFactoryBean();

        MessageSource messageSource = this.messageSource();

        if (messageSource != null) {
            validatorFactory.setValidationMessageSource(messageSource);
        }

        return validatorFactory;
    }

    @Bean
    SecureRandom secureRandom() {
        return new SecureRandom();
    }

    @Bean
    MessageSource messageSource() {
        var messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(Env.MESSAGE_SOURCE_BASENAME);
        messageSource.setDefaultEncoding(Env.MESSAGE_SOURCE_DEFAULT_ENCODING);
        return messageSource;
    }

}
