package ibs124.gundi.service.utility.impl;

import static ibs124.gundi.constant.ThTemplates.NEW_USER_VERIFICATION_EMAIL;

import java.util.Map;

import static ibs124.gundi.constant.Env.GUNDI_LOGO_URL;
import static ibs124.gundi.constant.ThEnv.DEADLINE;
import static ibs124.gundi.constant.ThEnv.TOKEN;
import static ibs124.gundi.constant.ThEnv.EXPIRATION;
import static ibs124.gundi.constant.ThEnv.URL;

import org.springframework.stereotype.Service;

import ibs124.gundi.configuration.PropertyConfig;
import ibs124.gundi.model.application.EmailSendDto;
import ibs124.gundi.model.application.TemplateCompileDto;
import ibs124.gundi.model.application.VerificationSendDto;
import ibs124.gundi.model.enumm.VerificationType;
import ibs124.gundi.model.properties.VerificationEmailProperties;
import ibs124.gundi.model.properties.VerificationProperties;
import ibs124.gundi.model.properties.VerificationTokenProperties;
import ibs124.gundi.service.utility.EmailSendingService;
import ibs124.gundi.service.utility.TemplateCompileService;
import ibs124.gundi.service.utility.VerificationSendingService;

@Service
class VerificationSendingServiceImpl implements VerificationSendingService {

    private final TemplateCompileService templateCompileService;
    private final EmailSendingService emailSendingService;
    private final Map<VerificationType, VerificationProperties> configMap;

    public VerificationSendingServiceImpl(
            TemplateCompileService templateCompileService,
            EmailSendingService emailSendingService,
            PropertyConfig config) {

        this.templateCompileService = templateCompileService;

        this.emailSendingService = emailSendingService;

        this.configMap = Map.of(
                VerificationType.NEW_USER, config.newUser());
    }

    @Override
    public void sendVerification(VerificationSendDto request) {
        VerificationProperties config = this.configMap.get(request.type());

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
