package ibs124.gundi.console.test.verification;

import static ibs124.gundi.console.test.verification.Config.*;

import org.springframework.stereotype.Component;

import ibs124.gundi.configuration.PropertyConfig;
import ibs124.gundi.console.CommandRunner;
import ibs124.gundi.model.application.EmailSendDto;
import ibs124.gundi.model.application.TemplateCompileDto;
import ibs124.gundi.model.properties.MailProperties;
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
        TemplateCompileDto template = new TemplateCompileDto(
                TEMPLATE, TEMPALTE_ATTRIBUTES);

        String message = this.templateCompileService
                .compileHtml(template);

        MailProperties mail = this.properties.newUser().mail();

        EmailSendDto emailRequest = new EmailSendDto(
                mail.from(),
                mail.displayName(),
                MAIL_TO,
                mail.subject(),
                message,
                mail.isHtml());

        this.emailSendingService.sendEmail(emailRequest);

        return MAIL_SENT_MESSAGE;
    }

}
