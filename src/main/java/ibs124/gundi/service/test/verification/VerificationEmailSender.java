package ibs124.gundi.service.test.verification;

import static ibs124.gundi.service.test.verification.Config.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import ibs124.gundi.model.application.EmailSendDto;
import ibs124.gundi.model.application.TemplateCompileDto;
import ibs124.gundi.service.utility.EmailSendingService;
import ibs124.gundi.service.utility.TemplateCompileService;

@Component
public class VerificationEmailSender {

    private final EmailSendingService emailSendingService;
    private final TemplateCompileService templateCompileService;

    public VerificationEmailSender(
            EmailSendingService emailSendingService,
            TemplateCompileService templateCompileService) {
        this.emailSendingService = emailSendingService;
        this.templateCompileService = templateCompileService;
    }

    public void sendTestingVerificationTemail() {
        TemplateCompileDto templateRequest = new TemplateCompileDto(
                URL_HTML, null);

        templateRequest.addAttributre(KEY_VERIFICATION_TOKEN, VERIFICATION_CODE);
        templateRequest.addAttributre(KEY_CSS_INLINE, this.loadCss());
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

    }

    private String loadCss() {
        try (InputStream is = this.getClassPathResource(URL_CSS).getInputStream()) {
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private ClassPathResource getClassPathResource(String path) {
        return new ClassPathResource(path);
    }

}
