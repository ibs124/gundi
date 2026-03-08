package ibs124.gundi.event.listener;

import static ibs124.gundi.constant.Templates.NEW_USER_VERIFICATION_EMAIL;

import static ibs124.gundi.constant.Env.GUNDI_LOGO_URL;
import static ibs124.gundi.constant.ThymeleafEnv.DEADLINE;
import static ibs124.gundi.constant.ThymeleafEnv.TOKEN;
import static ibs124.gundi.constant.ThymeleafEnv.EXPIRATION;
import static ibs124.gundi.constant.ThymeleafEnv.URL;

import ibs124.gundi.model.application.EmailSendDto;
import ibs124.gundi.model.application.TemplateCompileDto;
import ibs124.gundi.model.properties.VerificationEmailProperties;
import ibs124.gundi.model.properties.VerificationProperties;
import ibs124.gundi.model.properties.VerificationTokenProperties;
import ibs124.gundi.service.message.EmailSendingService;
import ibs124.gundi.service.message.TemplateCompilingService;
import ibs124.gundi.util.TestUtils;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import ibs124.gundi.config.PropertyConfig;
import ibs124.gundi.event.NewUserVerificationEvent;

@Component
class NewUserVerificationEventListener
        implements ApplicationListener<NewUserVerificationEvent> {

    private final TemplateCompilingService templateCompileService;
    private final EmailSendingService emailSendingService;
    private final PropertyConfig config;

    public NewUserVerificationEventListener(
            TemplateCompilingService templateCompileService,
            EmailSendingService emailSendingService,
            PropertyConfig config) {
        this.templateCompileService = templateCompileService;
        this.emailSendingService = emailSendingService;
        this.config = config;
    }

    @Override
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onApplicationEvent(NewUserVerificationEvent event) {

        TestUtils.sendVerification(event);

        // VerificationProperties config = this.config.newUser();

        // VerificationTokenProperties tokenConfig = config.token();

        // TemplateCompileDto templateRequest = new TemplateCompileDto(
        //         NEW_USER_VERIFICATION_EMAIL)
        //         .addVariable(URL, GUNDI_LOGO_URL)
        //         .addVariable(TOKEN, event.getSecret())
        //         .addVariable(EXPIRATION, tokenConfig.expirationMinutes())
        //         .addVariable(DEADLINE, config.timeframeHours());

        // String message = this.templateCompileService.compileHtml(templateRequest);

        // VerificationEmailProperties mailConfig = config.mail();

        // EmailSendDto emailRequest = EmailSendDto
        //         .builder()
        //         .from(mailConfig.from())
        //         .displayName(mailConfig.displayName())
        //         .to(event.getEmail())
        //         .subject(mailConfig.subject())
        //         .text(message)
        //         .isHtml(mailConfig.isHtml())
        //         .build();

        // this.emailSendingService.sendEmail(emailRequest);

    }

}
