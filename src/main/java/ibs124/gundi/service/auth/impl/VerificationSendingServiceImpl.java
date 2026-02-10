package ibs124.gundi.service.auth.impl;

import static ibs124.gundi.constant.Templates.NEW_USER_VERIFICATION_EMAIL;

import static ibs124.gundi.constant.Env.GUNDI_LOGO_URL;
import static ibs124.gundi.constant.ThymeleafEnv.DEADLINE;
import static ibs124.gundi.constant.ThymeleafEnv.TOKEN;
import static ibs124.gundi.constant.ThymeleafEnv.EXPIRATION;
import static ibs124.gundi.constant.ThymeleafEnv.URL;

import org.springframework.stereotype.Service;

import ibs124.gundi.configuration.PropertyConfiguration;
import ibs124.gundi.model.application.EmailSendDto;
import ibs124.gundi.model.application.TemplateCompileDto;
import ibs124.gundi.model.application.VerificationSendDto;
import ibs124.gundi.model.properties.VerificationEmailProperties;
import ibs124.gundi.model.properties.VerificationProperties;
import ibs124.gundi.model.properties.VerificationTokenProperties;
import ibs124.gundi.service.auth.EmailSendingService;
import ibs124.gundi.service.auth.TemplateCompilingService;
import ibs124.gundi.service.auth.VerificationSendingService;

@Service
class VerificationSendingServiceImpl implements VerificationSendingService {

    private final TemplateCompilingService templateCompileService;
    private final EmailSendingService emailSendingService;
    private final PropertyConfiguration config;

    public VerificationSendingServiceImpl(
            TemplateCompilingService templateCompileService,
            EmailSendingService emailSendingService,
            PropertyConfiguration config) {
        this.templateCompileService = templateCompileService;
        this.emailSendingService = emailSendingService;
        this.config = config;
    }

    @Override
    public void sendVerification(VerificationSendDto request) {
        VerificationProperties config = this.config.newUser();

        VerificationTokenProperties tokenConfig = config.token();

        TemplateCompileDto templateRequest = new TemplateCompileDto(
                NEW_USER_VERIFICATION_EMAIL)
                .addVariable(URL, GUNDI_LOGO_URL)
                .addVariable(TOKEN, request.token())
                .addVariable(EXPIRATION, tokenConfig.expirationMinutes())
                .addVariable(DEADLINE, config.timeframeHours());

        String message = this.templateCompileService.compileHtml(templateRequest);

        VerificationEmailProperties mailConfig = config.mail();

        EmailSendDto emailRequest = EmailSendDto
                .builder()
                .from(mailConfig.from())
                .displayName(mailConfig.displayName())
                .to(request.email())
                .subject(mailConfig.subject())
                .text(message)
                .isHtml(mailConfig.isHtml())
                .build();

        this.emailSendingService.sendEmail(emailRequest);
    }

}
