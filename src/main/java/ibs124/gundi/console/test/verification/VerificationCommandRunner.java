package ibs124.gundi.console.test.verification;

import static ibs124.gundi.constant.ThEnv.DEADLINE;
import static ibs124.gundi.constant.ThEnv.TOKEN;
import static ibs124.gundi.constant.ThEnv.EXPIRATION;
import static ibs124.gundi.constant.ThEnv.URL;

import java.util.Map;

import org.springframework.stereotype.Component;

import ibs124.gundi.configuration.PropertyConfig;
import ibs124.gundi.console.CommandRunner;
import ibs124.gundi.constant.Env;
import ibs124.gundi.constant.ThTemplates;
import ibs124.gundi.model.application.EmailSendDto;
import ibs124.gundi.model.application.TemplateCompileDto;
import ibs124.gundi.model.properties.VerificationProperties;
import ibs124.gundi.service.utility.EmailSendingService;
import ibs124.gundi.service.utility.TemplateCompileService;

@Component
public class VerificationCommandRunner implements CommandRunner {

    private final EmailSendingService emailSendingService;
    private final TemplateCompileService templateCompileService;
    private final VerificationProperties config;

    public VerificationCommandRunner(
            EmailSendingService emailSendingService,
            TemplateCompileService templateCompileService,
            PropertyConfig config) {
        this.emailSendingService = emailSendingService;
        this.templateCompileService = templateCompileService;
        this.config = config.newUser();
    }

    @Override
    public String run(String... args) {
        TemplateCompileDto template = new TemplateCompileDto(
                ThTemplates.NEW_USER_VERIFICATION_EMAIL,
                Map.of(
                        URL, Env.GUNDI_LOGO_URL,
                        TOKEN, Config.OTP,
                        EXPIRATION, this.config.tokenExpirationMinutes(),
                        DEADLINE, this.config.verificationDeadlineHours()));

        String message = this.templateCompileService.compileHtml(template);

        EmailSendDto email = new EmailSendDto(
                this.config.mail(), message, Config.EMAIL_TO);

        this.emailSendingService.sendEmail(email);

        return Config.MESSAGE;
    }

}
