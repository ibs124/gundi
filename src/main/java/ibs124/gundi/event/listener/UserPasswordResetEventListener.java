package ibs124.gundi.event.listener;

import static ibs124.gundi.constant.Templates.AUTH_VERIFICATION_EMAIL;

import static ibs124.gundi.constant.Env.GUNDI_LOGO_URL;
import static ibs124.gundi.constant.ThymeleafEnv.TOKEN;
import static ibs124.gundi.constant.ThymeleafEnv.EXPIRATION;
import static ibs124.gundi.constant.ThymeleafEnv.URL;

import ibs124.gundi.model.config.VerificationEmailProperties;
import ibs124.gundi.model.config.VerificationProperties;
import ibs124.gundi.model.config.VerificationTokenProperties;
import ibs124.gundi.model.dto.auth.EmailSendDto;
import ibs124.gundi.model.dto.auth.TemplateCompileDto;
import ibs124.gundi.service.message.EmailSendingService;
import ibs124.gundi.service.message.TemplateCompilingService;
import ibs124.gundi.util.TestUtils;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.web.util.UriComponentsBuilder;

import ibs124.gundi.config.PropertyConfig;
import ibs124.gundi.constant.Routes;
import ibs124.gundi.event.UserPasswordResetEvent;

@Component
class UserVerificationEventListener
        implements ApplicationListener<UserPasswordResetEvent> {

    private final TemplateCompilingService templateCompileService;
    private final EmailSendingService emailSendingService;
    private final PropertyConfig config;

    public UserVerificationEventListener(
            TemplateCompilingService templateCompileService,
            EmailSendingService emailSendingService,
            PropertyConfig config) {
        this.templateCompileService = templateCompileService;
        this.emailSendingService = emailSendingService;
        this.config = config;
    }

    @Override
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onApplicationEvent(UserPasswordResetEvent event) {

        VerificationProperties config = this.config.passwordReset();
        VerificationTokenProperties tokenConfig = config.token();

        if (tokenConfig.useLink()) {
            event = this.mapTokenToMagicLink(event);
        }

        if (this.config.debug().passwordReset()) {
            TestUtils.sendVerification(event);
            return;
        }

        TemplateCompileDto templateRequest = new TemplateCompileDto(
                AUTH_VERIFICATION_EMAIL)
                .addVariable(URL, GUNDI_LOGO_URL)
                .addVariable(TOKEN, event.getSecret())
                .addVariable(EXPIRATION, tokenConfig.expirationMinutes());

        String message = this.templateCompileService.compileHtml(templateRequest);

        VerificationEmailProperties mailConfig = config.mail();

        EmailSendDto emailRequest = EmailSendDto
                .builder()
                .from(mailConfig.from())
                .displayName(mailConfig.displayName())
                .to(event.getEmail())
                .subject(mailConfig.subject())
                .text(message)
                .isHtml(mailConfig.isHtml())
                .build();

        this.emailSendingService.sendEmail(emailRequest);
    }

    private UserPasswordResetEvent mapTokenToMagicLink(UserPasswordResetEvent event) {
        String link = UriComponentsBuilder
                .fromUriString(event.getAppUrl())
                .path(Routes.AUTH_PASSWORD_RESSET_SUBMIT)
                .queryParam(Routes.VAR_TOKEN, event.getSecret())
                .build()
                .toUriString();

        return new UserPasswordResetEvent(
                event.getEmail(), link, event.getAppUrl());
    }
}
