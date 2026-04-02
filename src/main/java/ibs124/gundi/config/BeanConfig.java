package ibs124.gundi.config;

import java.security.SecureRandom;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.thymeleaf.spring6.SpringTemplateEngine;

import ibs124.gundi.common.email_sender.EmailSender;
import ibs124.gundi.common.email_sender.EmailSenderFactory;
import ibs124.gundi.common.template_compiler.TemplateCompiler;
import ibs124.gundi.common.template_compiler.TemplateCompilerFactory;
import ibs124.gundi.common.token_generator.TokenGenerator;
import ibs124.gundi.common.token_generator.TokenGeneratorFactory;
import ibs124.gundi.constant.Env;

@Configuration
public class BeanConfig {

    @Bean
    EmailSender emailSender(JavaMailSender javaMailSender) {
        return EmailSenderFactory.build(javaMailSender);
    }

    @Bean
    TemplateCompiler templateCompiler(SpringTemplateEngine templateEngine) {
        return TemplateCompilerFactory.build(templateEngine);
    }

    @Bean
    TokenGenerator tokenGenerator(SecureRandom random, PropertyConfig config) {
        return TokenGeneratorFactory
                .getInstance()
                .addSecureRandom(random)
                .addRequest(Env.REQUEST_KEY_VERIFICATION, config.verification().token())
                .addRequest(Env.REQUEST_KEY_PASSWORD_RESET, config.passwordReset().token())
                .build();
    }

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
