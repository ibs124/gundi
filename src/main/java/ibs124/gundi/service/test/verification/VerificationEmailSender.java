package ibs124.gundi.service.test.verification;

import static ibs124.gundi.service.test.verification.Config.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import ibs124.gundi.model.application.EmailSendDto;
import ibs124.gundi.model.application.TemplateCompileDto;
import ibs124.gundi.service.utility.EmailSendingService;
import ibs124.gundi.service.utility.TemplateCompileService;

@Component
public class VerificationEmailSender {

    private final EmailSendingService emailSendingService;
    private final TemplateCompileService templateCompileService;
    private final Resource cssResource;

    public VerificationEmailSender(
            EmailSendingService emailSendingService,
            TemplateCompileService templateCompileService,
            @Value(PATH_CSS) Resource cssResource) {
        this.emailSendingService = emailSendingService;
        this.templateCompileService = templateCompileService;
        this.cssResource = cssResource;
    }

    public void run() {
        String css = this.loadCss();

        TemplateCompileDto templateRequest = new TemplateCompileDto(
                PATH_HTML, null);

        templateRequest.addAttributre(KEY_CSS_INLINE, css);
        templateRequest.addAttributre(KEY_VERIFICATION_TOKEN, VERIFICATION_CODE);

        String htmlMessage = this.templateCompileService.compileHtml(templateRequest);

        EmailSendDto emailRequest = new EmailSendDto(
                EMAIL_FROM,
                EMAIL_DISPLAY_NAME,
                EMAIL_TO,
                EMAIL_SUBJECT,
                htmlMessage,
                true);

        this.emailSendingService.sendEmail(emailRequest);

    }

    private String loadCss() {
        try (InputStream is = this.cssResource.getInputStream()) {
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }

}
