package ibs124.gundi.console.test.verification;

import static ibs124.gundi.console.test.verification.Config.*;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ibs124.gundi.console.CommandRunner;
import ibs124.gundi.model.application.EmailSendDto;
import ibs124.gundi.model.application.TemplateCompileDto;
import ibs124.gundi.service.utility.EmailSendingService;
import ibs124.gundi.service.utility.TemplateCompileService;

@Component
public class VerificationCommandRunner implements CommandRunner {

    private final EmailSendingService emailSendingService;
    private final TemplateCompileService templateCompileService;

    public VerificationCommandRunner(
            EmailSendingService emailSendingService,
            TemplateCompileService templateCompileService) {
        this.emailSendingService = emailSendingService;
        this.templateCompileService = templateCompileService;
    }

    @Override
    public String run(String... args) {
        TemplateCompileDto templateRequest = new TemplateCompileDto(
                URL_HTML, null);

        templateRequest.addAttributre(KEY_VERIFICATION_TOKEN, VERIFICATION_CODE);
        templateRequest.addAttributre(KEY_URL_LOGO, URL_LOGO);

        String htmlMessage = this.templateCompileService.compileHtml(templateRequest);

        EmailSendDto emailRequest = new EmailSendDto(
                EMAIL_FROM,
                EMAIL_DISPLAY_NAME,
                EMAIL_TO,
                EMAIL_SUBJECT,
                htmlMessage,
                true);

        this.emailSendingService.sendEmail(emailRequest);

        String recipients = Arrays.stream(EMAIL_TO).collect(Collectors.joining(", "));

        return "Verification email sent, check inboxes at: " + recipients;
    }

}
