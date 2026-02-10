package ibs124.gundi.event.listener;

import static ibs124.gundi.constant.Templates.NEW_USER_VERIFICATION_EMAIL;

import static ibs124.gundi.constant.Env.GUNDI_LOGO_URL;
import static ibs124.gundi.constant.ThymeleafEnv.DEADLINE;
import static ibs124.gundi.constant.ThymeleafEnv.TOKEN;
import static ibs124.gundi.constant.ThymeleafEnv.EXPIRATION;
import static ibs124.gundi.constant.ThymeleafEnv.URL;

import ibs124.gundi.configuration.PropertyConfiguration;
import ibs124.gundi.model.application.EmailSendDto;
import ibs124.gundi.model.application.TemplateCompileDto;
import ibs124.gundi.model.application.VerificationSendDto;
import ibs124.gundi.model.properties.VerificationEmailProperties;
import ibs124.gundi.model.properties.VerificationProperties;
import ibs124.gundi.model.properties.VerificationTokenProperties;
import ibs124.gundi.service.auth.EmailSendingService;
import ibs124.gundi.service.auth.TemplateCompilingService;

import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import ibs124.gundi.event.UserVerificationEvent;

@Component
class UserVerificationEventListener
        implements ApplicationListener<UserVerificationEvent> {

    private final TemplateCompilingService templateCompileService;
    private final EmailSendingService emailSendingService;
    private final PropertyConfiguration config;

    public UserVerificationEventListener(
            TemplateCompilingService templateCompileService,
            EmailSendingService emailSendingService,
            PropertyConfiguration config) {
        this.templateCompileService = templateCompileService;
        this.emailSendingService = emailSendingService;
        this.config = config;
    }

    @Override
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onApplicationEvent(@NonNull UserVerificationEvent event) {
        VerificationSendDto payload = event.getPayload();

        VerificationProperties config = this.config.newUser();

        VerificationTokenProperties tokenConfig = config.token();

        TemplateCompileDto templateRequest = new TemplateCompileDto(
                NEW_USER_VERIFICATION_EMAIL)
                .addVariable(URL, GUNDI_LOGO_URL)
                .addVariable(TOKEN, payload.token())
                .addVariable(EXPIRATION, tokenConfig.expirationMinutes())
                .addVariable(DEADLINE, config.timeframeHours());

        String message = this.templateCompileService.compileHtml(templateRequest);

        VerificationEmailProperties mailConfig = config.mail();

        EmailSendDto emailRequest = EmailSendDto
                .builder()
                .from(mailConfig.from())
                .displayName(mailConfig.displayName())
                .to(payload.email())
                .subject(mailConfig.subject())
                .text(message)
                .isHtml(mailConfig.isHtml())
                .build();

        this.emailSendingService.sendEmail(emailRequest);

    }

}
