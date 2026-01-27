package ibs124.gundi.console.test.verification;

import static ibs124.gundi.console.test.verification.Config.*;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import ibs124.gundi.configuration.PropertyConfig;
import ibs124.gundi.console.CommandRunner;
import ibs124.gundi.model.application.EmailSendDto;
import ibs124.gundi.model.application.TemplateCompileDto;
import ibs124.gundi.model.properties.MailProperties;
import ibs124.gundi.model.properties.VerificationProperties;
import ibs124.gundi.service.utility.EmailSendingService;
import ibs124.gundi.service.utility.TemplateCompileService;

@Component
public class VerificationCommandRunner implements CommandRunner {

    private final EmailSendingService emailSendingService;
    private final TemplateCompileService templateCompileService;
    private final PropertyConfig properties;

    public VerificationCommandRunner(
            EmailSendingService emailSendingService,
            TemplateCompileService templateCompileService,
            PropertyConfig config) {
        this.emailSendingService = emailSendingService;
        this.templateCompileService = templateCompileService;
        this.properties = config;
    }

    @Override
    public String run(String... args) {
        String message = this.createMessage();

        this.sendMessage(message);

        return MAIL_SENT_MESSAGE;
    }

    private void sendMessage(String message) {
        MailProperties mail = this.properties.newUser().mail();

        EmailSendDto emailRequest = new EmailSendDto(
                mail.from(),
                mail.displayName(),
                MAIL_TO,
                mail.subject(),
                message,
                mail.isHtml());

        this.emailSendingService.sendEmail(emailRequest);

    }

    private String createMessage() {
        Map<String, Object> map = new HashMap<>();

        VerificationProperties newUser = this.properties.newUser();

        map.putAll(TEMPALTE_ATTRIBUTES);
        map.put(KEY_EXPIRATION, newUser.tokenExpirationMinutes());
        map.put(KEY_DEADLINE, newUser.verificationDeadlineHours());

        TemplateCompileDto template = new TemplateCompileDto(
                TEMPLATE, map);

        return this.templateCompileService.compileHtml(template);
    }

}
