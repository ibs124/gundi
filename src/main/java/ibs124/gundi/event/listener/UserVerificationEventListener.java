package ibs124.gundi.event.listener;

import static ibs124.gundi.constant.ThTemplates.NEW_USER_VERIFICATION_EMAIL;
import static ibs124.gundi.constant.ThEnv.DEADLINE;
import static ibs124.gundi.constant.ThEnv.TOKEN;
import static ibs124.gundi.constant.ThEnv.EXPIRATION;
import static ibs124.gundi.constant.ThEnv.URL;

import java.util.Map;

import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import ibs124.gundi.configuration.PropertyConfig;
import ibs124.gundi.constant.Env;
import ibs124.gundi.event.UserVerificationEvent;
import ibs124.gundi.model.application.EmailSendDto;
import ibs124.gundi.model.application.TemplateCompileDto;
import ibs124.gundi.model.properties.VerificationProperties;
import ibs124.gundi.service.utility.EmailSendingService;
import ibs124.gundi.service.utility.TemplateCompileService;

@Component
class UserVerificationEventListener
        implements ApplicationListener<UserVerificationEvent> {

    private final VerificationProperties config;
    private final TemplateCompileService templateCompileService;
    private final EmailSendingService emailSendingService;

    public UserVerificationEventListener(
            TemplateCompileService templateCompileService,
            EmailSendingService emailSendingService,
            PropertyConfig config) {
        this.templateCompileService = templateCompileService;
        this.emailSendingService = emailSendingService;
        this.config = config.newUser();
    }

    @Override
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onApplicationEvent(@NonNull UserVerificationEvent event) {

        TemplateCompileDto template = new TemplateCompileDto(
                NEW_USER_VERIFICATION_EMAIL,
                Map.of(
                        URL, Env.GUNDI_LOGO_URL,
                        TOKEN, event.getToken(),
                        EXPIRATION, this.config.tokenExpirationMinutes(),
                        DEADLINE, this.config.verificationDeadlineHours()));

        String message = this.templateCompileService.compileHtml(template);

        EmailSendDto email = new EmailSendDto(
                this.config.mail(), message, event.getEmail());

        this.emailSendingService.sendEmail(email);
    }

}
